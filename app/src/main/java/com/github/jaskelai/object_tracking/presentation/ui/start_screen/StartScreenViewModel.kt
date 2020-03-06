package com.github.jaskelai.object_tracking.presentation.ui.start_screen

import androidx.lifecycle.LiveData
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import javax.inject.Inject

class StartScreenViewModel @Inject constructor() : BaseViewModel() {

    private val _signInNavigationLiveData = SingleEventLiveData(false)

    val signInNavigationLiveData: LiveData<Boolean> = _signInNavigationLiveData

    fun onSignInButtonClicked() {
        _signInNavigationLiveData.value = true
    }
}
