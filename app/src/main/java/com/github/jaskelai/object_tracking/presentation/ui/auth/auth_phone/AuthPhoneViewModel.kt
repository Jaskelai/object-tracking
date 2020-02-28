package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.mapper.FirebaseErrorMapper
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ext.onlyDigits
import com.google.firebase.FirebaseException
import javax.inject.Inject

const val TIMEOUT_DURATION_SECONDS = 15L

class AuthPhoneViewModel @Inject constructor(
    private val firebaseErrorMapper: FirebaseErrorMapper
) : BaseViewModel() {

    private val _backNavigationLiveData = SingleEventLiveData<Boolean>()
    private val _toSmsCodeNavigationLiveData = SingleEventLiveData<Boolean>()
    private val _onSendSmsButtonClickedLiveData = SingleEventLiveData<Boolean>()
    private val _isSendSmsButtonEnabledLiveData = MutableLiveData<Boolean>()

    val backNavigationLiveData: LiveData<Boolean> = _backNavigationLiveData
    val toSmsCodeNavigationLiveData: LiveData<Boolean> = _toSmsCodeNavigationLiveData
    val onSendSmsButtonClickedLiveData: LiveData<Boolean> = _onSendSmsButtonClickedLiveData
    val isSendSmsButtonEnabledLiveData: LiveData<Boolean> = _isSendSmsButtonEnabledLiveData

    var phoneNumber: String = PHONE_NUMBER_PLACEHOLDER
        set(value) {
            field = value
            onPhoneNumberTyped(field)

        }

    companion object {
        private const val PHONE_NUMBER_PLACEHOLDER = "+7"
        private const val PHONE_NUMBER_LENGTH = 11
    }

    init {
        _progressLiveData.value = true
        _backNavigationLiveData.value = false
        _isSendSmsButtonEnabledLiveData.value = false
        _onSendSmsButtonClickedLiveData.value = false
    }

    fun onBackButtonClicked() {
        _backNavigationLiveData.value = true
    }

    fun onSignInButtonClicked() {
        _onSendSmsButtonClickedLiveData.value = true
        _isSendSmsButtonEnabledLiveData.value = false
    }

    fun onVerificationComplete() {
        //TODO move to enter sms
    }

    fun onVerificationFailed(ex: FirebaseException) {
        _errorMessageLiveData.value = firebaseErrorMapper.mapExceptionToResourceId(ex)
    }

    fun onVerificationTimeout() {
        _errorMessageLiveData.value = R.string.error_timeout
    }

    private fun onPhoneNumberTyped(phoneNumber: String) {
        val actualPhoneNumber = phoneNumber.onlyDigits
        _isSendSmsButtonEnabledLiveData.value = actualPhoneNumber.length == PHONE_NUMBER_LENGTH
    }
}
