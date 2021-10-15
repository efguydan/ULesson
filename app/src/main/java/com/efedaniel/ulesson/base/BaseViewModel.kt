package com.efedaniel.ulesson.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.efedaniel.ulesson.networkutils.LoadingStatus

abstract class BaseViewModel : ViewModel() {

    protected val _loadingStatus = MutableLiveData<LoadingStatus>()

    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus
}
