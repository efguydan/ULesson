package com.efedaniel.ulesson.ulessonapp.screens.me

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.efedaniel.ulesson.base.BaseViewModel
import com.efedaniel.ulesson.networkutils.LoadingStatus
import com.efedaniel.ulesson.networkutils.Result
import com.efedaniel.ulesson.ulessonapp.data.repositories.ULessonRepository
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.ulessonapp.models.local.toLessonModel
import com.efedaniel.ulesson.utils.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class MeViewModel @Inject constructor(
    private val repository: ULessonRepository
) : BaseViewModel() {

    private val _subjectList = MutableLiveData(Constants.Data.SUBJECTS)
    val subjectList: LiveData<List<String>> = _subjectList

    private val _selectedSubject = MutableLiveData<String>()

    private val _myLessons = Transformations.switchMap(_selectedSubject) {
        repository.observeMySubjectsFromCache(it)
    }
    val myLessons: LiveData<List<Lesson>> = Transformations.map(_myLessons) { localLessons ->
        localLessons.map { it.toLessonModel() }
    }

    init {
        getMyLessonsFromNetwork()
    }

    private fun getMyLessonsFromNetwork() {
        _loadingStatus.setValue(LoadingStatus.Loading())
        viewModelScope.launch {
            when (val result = repository.getMyLessons()) {
                is Result.Success -> {
                    _loadingStatus.setValue(LoadingStatus.Success)
                }
                is Result.Error -> {
                    _loadingStatus.setValue(LoadingStatus.Error(result.errorMessage))
                }
            }
        }
    }

    fun onSubjectSelected(position: Int) {
        _selectedSubject.value = Constants.Data.SUBJECTS[position]
    }
}
