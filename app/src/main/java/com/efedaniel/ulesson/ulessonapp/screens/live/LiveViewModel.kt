package com.efedaniel.ulesson.ulessonapp.screens.live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.efedaniel.ulesson.base.BaseViewModel
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.ulessonapp.data.repositories.ULessonRepository
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.utils.Constants
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class LiveViewModel @Inject constructor(
    private val repository: ULessonRepository
) : BaseViewModel() {

    private val _promotedLessons = MutableLiveData<List<Lesson>>()
    val promotedLessons: LiveData<List<Lesson>> = _promotedLessons

    private var allLiveLessons: MutableList<Lesson> = mutableListOf()
    private val _liveLessons = MutableLiveData<List<Lesson>>(emptyList())
    val liveLessons: LiveData<List<Lesson>> = _liveLessons

    private val _subjectList = MutableLiveData<List<String>>()
    val subjectList: LiveData<List<String>> = _subjectList

    init {
        getLiveAndPromotedLessons()
    }

    private fun getLiveAndPromotedLessons() {
        _loadingStatus.value = LoadingStatus.Loading()
        viewModelScope.launch {
            // Make the API calls simultaneously
            val promotedLessonsTask = async { repository.getPromotedLessons() }
            val liveLessonsTask = async { repository.getLiveLessons() }

            val promotedLessonsList = promotedLessonsTask.await()
            val liveLessonsList = liveLessonsTask.await()

            if (promotedLessonsList is Result.Success && liveLessonsList is Result.Success) {
                _loadingStatus.value = LoadingStatus.Success
                _promotedLessons.postValue(promotedLessonsList.data)
                handleLiveLessonsData(liveLessonsList.data)
            } else {
                // TODO Show error
            }
        }
    }

    private fun handleLiveLessonsData(lessons: List<Lesson>) {
        allLiveLessons.clear()
        allLiveLessons.addAll(lessons)

        _liveLessons.postValue(lessons)

        _subjectList.postValue(Constants.Data.SUBJECTS)
    }

    fun onSubjectSelected(position: Int) {
        _liveLessons.value = if (position == 0) {
            allLiveLessons
        } else {
            allLiveLessons.filter {
                it.subjectName.equals(Constants.Data.SUBJECTS[position], ignoreCase = true)
            }
        }
    }
}
