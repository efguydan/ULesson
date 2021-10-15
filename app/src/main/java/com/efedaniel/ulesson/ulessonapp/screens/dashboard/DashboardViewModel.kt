package com.efedaniel.ulesson.ulessonapp.screens.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.efedaniel.ulesson.base.BaseViewModel
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.networkutils.toLoadingStatus
import com.efedaniel.ulesson.ulessonapp.data.repositories.LocalRepository
import com.efedaniel.ulesson.ulessonapp.data.repositories.ULessonRepository
import com.efedaniel.ulesson.ulessonapp.models.general.RecentlyWatched
import com.efedaniel.ulesson.ulessonapp.models.general.Subject
import com.efedaniel.ulesson.ulessonapp.models.local.LocalRecentlyWatched
import com.efedaniel.ulesson.ulessonapp.models.local.LocalSubject
import com.efedaniel.ulesson.ulessonapp.models.local.toRecentlyWatched
import com.efedaniel.ulesson.ulessonapp.models.local.toSubject
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel @Inject constructor(
    private val uLessonRepository: ULessonRepository,
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val localSubjectList: LiveData<List<LocalSubject>> = localRepository.getAllSubjectsLive()
    val subjectList: LiveData<List<Subject>> = Transformations.map(localSubjectList) { list ->
        list.map { it.toSubject() }
    }

    val itemCount = MutableLiveData(2)
    private val _recentlyWatchedList: LiveData<List<LocalRecentlyWatched>> = Transformations.switchMap(itemCount) {
        localRepository.getRecentlyWatchedLessons(it)
    }
    val recentlyWatchedList: LiveData<List<RecentlyWatched>> = Transformations.map(_recentlyWatchedList) { list ->
        list.map { it.toRecentlyWatched() }
    }

    fun getSubjects(forceRefresh: Boolean = false) {
        if (subjectList.value?.isNotEmpty() == true && !forceRefresh) return
        viewModelScope.launch {
            val cache = localRepository.getAllSubjects()
            if (cache.isEmpty()) {
                _loadingStatus.value = LoadingStatus.Loading()
            }

            when (val result = uLessonRepository.getSubjects()) {
                is Result.Success -> {
                    _loadingStatus.value = LoadingStatus.Success
                }
                is Result.Error -> if (cache.isEmpty()) _loadingStatus.value = result.toLoadingStatus()
            }
        }
    }

    fun toggleRecentlyWatchedLimit() {
        if (itemCount.value == 2) {
            itemCount.value = 5
        } else {
            itemCount.value = 2
        }
    }

    fun insertRecentlyWatched(recentlyWatched: LocalRecentlyWatched) {
        viewModelScope.launch {
            localRepository.insertRecentlyWatched(recentlyWatched)
        }
    }
}
