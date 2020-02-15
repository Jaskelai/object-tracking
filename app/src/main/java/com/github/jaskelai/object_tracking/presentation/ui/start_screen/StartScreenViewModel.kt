package com.github.jaskelai.object_tracking.presentation.ui.start_screen

import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import javax.inject.Inject

class StartScreenViewModel @Inject constructor() : BaseViewModel() {

    val signInNavigationLiveData = SingleEventLiveData<Boolean>()

    init {
        signInNavigationLiveData.value = false
    }

    fun onSignInButtonClicked() {
        signInNavigationLiveData.value = true
    }
}
