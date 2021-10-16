package com.efedaniel.ulesson.ulessonapp.screens.live

import androidx.lifecycle.viewModelScope
import com.efedaniel.ulesson.base.BaseViewModel
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.ulessonapp.data.repositories.ULessonRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class LiveViewModel @Inject constructor(
    private val repository: ULessonRepository
): BaseViewModel() {

    init {
        getLiveAndPromotedLessons()
    }

    private fun getLiveAndPromotedLessons() {
        _loadingStatus.value = LoadingStatus.Loading()
        viewModelScope.launch {
            val promotedLessons = async { repository.getPromotedLessons() }
            val liveLessons = async { repository.getLiveLessons() }
            Timber.d("Promoted Lessons: ${promotedLessons.await()}")
            Timber.d("Live Lessons: ${liveLessons.await()}")
        }
    }

}