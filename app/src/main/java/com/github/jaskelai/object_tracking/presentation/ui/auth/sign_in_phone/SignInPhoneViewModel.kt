package com.github.jaskelai.object_tracking.presentation.ui.auth.sign_in_phone

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ext.onlyDigits
import javax.inject.Inject

class SignInPhoneViewModel @Inject constructor() : ViewModel() {

    val backNavigationLiveData = SingleEventLiveData<Boolean>()
    val isSignInButtonEnabledLiveData = MutableLiveData<Boolean>()

    var phoneNumber: String = PHONE_NUMBER_PLACEHOLDER
        get() = field
        set(value) {
            field = value
            onPhoneNumberTyped(field)

        }

    companion object {
        private const val PHONE_NUMBER_PLACEHOLDER = "+7"
        private const val PHONE_NUMBER_LENGTH = 11
    }

    init {
        backNavigationLiveData.value = false
        isSignInButtonEnabledLiveData.value = false
    }

    fun onBackButtonClicked() {
        backNavigationLiveData.value = true
    }

    fun onSignInButtonClicked(phoneNumber: String) {
        println("CLICK")
    }

    private fun onPhoneNumberTyped(phoneNumber: String) {
        val actualPhoneNumber = phoneNumber.onlyDigits
        isSignInButtonEnabledLiveData.value = actualPhoneNumber.length == PHONE_NUMBER_LENGTH
    }
}
