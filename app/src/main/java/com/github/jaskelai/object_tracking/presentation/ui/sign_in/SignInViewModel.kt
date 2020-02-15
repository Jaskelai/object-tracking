package com.github.jaskelai.object_tracking.presentation.ui.sign_in

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import javax.inject.Inject

class SignInViewModel @Inject constructor() : ViewModel() {

    val backNavigationLiveData = SingleEventLiveData<Boolean>()

    init {
        backNavigationLiveData.value = false
    }

    fun onBackButtonClicked() {
        backNavigationLiveData.value = true
    }
}
