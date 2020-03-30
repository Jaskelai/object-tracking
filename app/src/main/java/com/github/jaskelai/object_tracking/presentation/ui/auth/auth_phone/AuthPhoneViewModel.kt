package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.mapper.FirebaseErrorMapper
import com.github.jaskelai.object_tracking.domain.interactor.PhoneAuthInteractor
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ext.onlyDigits
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject

const val TIMEOUT_DURATION_SECONDS = 15L

class AuthPhoneViewModel @Inject constructor(
    private val firebaseErrorMapper: FirebaseErrorMapper,
    private val phoneAuthInteractor: PhoneAuthInteractor,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    companion object {
        private const val PHONE_NUMBER_PLACEHOLDER = "+7"
        private const val PHONE_NUMBER_LENGTH = 11
    }

    val toSmsCodeNavigationLiveData = SingleEventLiveData(false)
    val onSendSmsButtonClickedLiveData = MutableLiveData(false)
    val isSendSmsButtonEnabledLiveData = MutableLiveData(false)

    var phoneNumber: String = PHONE_NUMBER_PLACEHOLDER
        set(value) {
            field = value
            onPhoneNumberTyped(field)

        }

    private fun onPhoneNumberTyped(phoneNumber: String) {
        val actualPhoneNumber = phoneNumber.onlyDigits
        isSendSmsButtonEnabledLiveData.value =
            (actualPhoneNumber.length == PHONE_NUMBER_LENGTH && progressLiveData.value == false)
    }

    fun onSignInButtonClicked() {
        onSendSmsButtonClickedLiveData.value = true
        isSendSmsButtonEnabledLiveData.value = false
        progressLiveData.value = true
    }

    fun onVerificationComplete(credential: PhoneAuthCredential) {
        when (credential.smsCode) {
            null -> { }
            else -> {
                phoneAuthInteractor.setCredentialViaObject(credential)
                invalidateAfterRequest()
                toSmsCodeNavigationLiveData.value = true
            }
        }
    }

    fun onVerificationFailed(ex: FirebaseException) {
        errorMessageLiveData.value =
            resourceProvider.getString(firebaseErrorMapper.mapExceptionToResourceId(ex))
        invalidateAfterRequest()
    }

    fun onVerificationTimeout(verificationId: String) {
        errorMessageLiveData.value = resourceProvider.getString(R.string.error_timeout)
        phoneAuthInteractor.setVerificationId(verificationId)
        invalidateAfterRequest()
    }

    fun onCodeSent(verificationId: String) {
        phoneAuthInteractor.setVerificationId(verificationId)
    }

    private fun invalidateAfterRequest() {
        isSendSmsButtonEnabledLiveData.value = true
        onSendSmsButtonClickedLiveData.value = false
        progressLiveData.value = false
    }
}
