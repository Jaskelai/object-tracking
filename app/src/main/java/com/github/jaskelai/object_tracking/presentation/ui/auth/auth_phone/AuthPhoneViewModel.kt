package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.mapper.FirebaseAuthErrorMapper
import com.github.jaskelai.object_tracking.domain.interactor.PhoneAuthInteractor
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ext.onlyDigits
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TIMEOUT_DURATION_SECONDS = 15L

class AuthPhoneViewModel @Inject constructor(
    private val firebaseAuthErrorMapper: FirebaseAuthErrorMapper,
    private val phoneAuthInteractor: PhoneAuthInteractor,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    companion object {
        private const val PHONE_NUMBER_PLACEHOLDER = "+7"
        private const val PHONE_NUMBER_LENGTH = 11
    }

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
            null -> {
                launch {
                    phoneAuthInteractor.setCredentialViaObject(credential)

                    val result = phoneAuthInteractor.signIn(needSms = false)

                    invalidateAfterRequest()

                    handleSignInResult(result)
                }
            }
            else -> {
                phoneAuthInteractor.setCredentialViaObject(credential)
                invalidateAfterRequest()

                navigate(AuthPhoneFragmentDirections.actionAuthPhoneFragmentToAuthSmsFragment())
            }
        }
    }

    private fun handleSignInResult(result: Result<Unit, ErrorModel>) {
        when (result) {
            is Result.Success -> {
                navigate(AuthPhoneFragmentDirections.actionAuthPhoneFragmentToMainFragment())
            }
            is Result.Error -> {
                if (result.data?.message != null) {
                    errorMessageLiveData.value = result.data.message
                }
                if (result.data?.messageId != null)
                    errorMessageLiveData.value =
                        resourceProvider.getString(result.data.messageId)
            }
        }
    }

    fun onVerificationFailed(ex: FirebaseException) {
        errorMessageLiveData.value = resourceProvider.getString(firebaseAuthErrorMapper.execute(ex))
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
