package com.github.jaskelai.object_tracking.presentation.ui.start_screen

import androidx.lifecycle.LiveData
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import javax.inject.Inject

class StartScreenViewModel @Inject constructor() : BaseViewModel() {

    val _signInNavigationLiveData = SingleEventLiveData<Boolean>()

    val signInNavigationLiveData: LiveData<Boolean> = _signInNavigationLiveData

    init {
        _signInNavigationLiveData.value = false
    }

    fun onSignInButtonClicked() {
        _signInNavigationLiveData.value = true
    }
}
