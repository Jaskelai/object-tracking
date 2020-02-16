package com.github.jaskelai.object_tracking.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData

abstract class BaseViewModel : ViewModel() {

    protected val _errorMessageLiveData = SingleEventLiveData<Int>()
    protected val _progressLiveData = SingleEventLiveData<Boolean>()

    val errorMessageLiveData: LiveData<Int> = _errorMessageLiveData
    val progressLiveData: LiveData<Boolean> = _progressLiveData
}
