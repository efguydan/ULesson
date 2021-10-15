package com.efedaniel.ulesson.ulessonapp.screens.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.efedaniel.ulesson.base.BaseViewModel
import com.efedaniel.ulesson.ulessonapp.data.repositories.LocalRepository
import com.efedaniel.ulesson.ulessonapp.models.general.Chapter
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.ulessonapp.models.general.Subject
import com.efedaniel.ulesson.ulessonapp.models.general.toLocalRecentlyWatched
import kotlinx.coroutines.launch
import javax.inject.Inject

class SubjectViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : BaseViewModel() {

    private val _chapterList = MutableLiveData<List<Chapter>>()
    val chapterList: LiveData<List<Chapter>>
        get() = _chapterList

    fun getSubjectChapters(subject: Subject) {
        viewModelScope.launch {
            _chapterList.value = localRepository.getSubjectChapters(subject.id)
        }
    }

    fun insertRecentlyWatched(lesson: Lesson, subject: Subject) {
        viewModelScope.launch {
            localRepository.insertRecentlyWatched(lesson.toLocalRecentlyWatched(subject.name))
        }
    }
}
