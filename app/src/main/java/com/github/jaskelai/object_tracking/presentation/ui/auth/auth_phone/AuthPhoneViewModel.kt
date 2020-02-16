package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.domain.interactors.AuthPhoneInteractor
import com.github.jaskelai.object_tracking.domain.model.auth_send_sms.SendSmsResult
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ext.doAfterNext
import com.github.jaskelai.object_tracking.presentation.utils.ext.onlyDigits
import javax.inject.Inject

const val TIMEOUT_DURATION_SECONDS = 15L

class AuthPhoneViewModel @Inject constructor(
    private val authPhoneInteractor: AuthPhoneInteractor
) : BaseViewModel() {

    private val _backNavigationLiveData = SingleEventLiveData<Boolean>()
    private val _toSmsCodeNavigationLiveData = SingleEventLiveData<Boolean>()
    private val _onSendSmsButtonClickedLiveData = SingleEventLiveData<Boolean>()
    private val _isSendSmsButtonEnabledLiveData = MutableLiveData<Boolean>()

    var authResultLiveData: LiveData<SendSmsResult>

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
        _backNavigationLiveData.value = false
        _isSendSmsButtonEnabledLiveData.value = false
        _onSendSmsButtonClickedLiveData.value = false

        authResultLiveData = authPhoneInteractor.observeSendSmsCodeStatus().doAfterNext {
            when (it) {
                is SendSmsResult.CodeSent -> {
                }
                is SendSmsResult.Timeout -> {
                    _errorMessageLiveData.value = R.string.error_timeout
                    _isSendSmsButtonEnabledLiveData.value = true
                }
                is SendSmsResult.Error -> {
                    _errorMessageLiveData.value = it.error.messageId
                    _isSendSmsButtonEnabledLiveData.value = true
                }
                is SendSmsResult.Success -> {
                    _toSmsCodeNavigationLiveData.value = true
                    _isSendSmsButtonEnabledLiveData.value = true
                }
            }
        }
    }

    fun onBackButtonClicked() {
        _backNavigationLiveData.value = true
    }

    fun onSignInButtonClicked() {
        _onSendSmsButtonClickedLiveData.value = true
        _progressLiveData.value = true
        _isSendSmsButtonEnabledLiveData.value = false
    }

    private fun onPhoneNumberTyped(phoneNumber: String) {
        val actualPhoneNumber = phoneNumber.onlyDigits
        _isSendSmsButtonEnabledLiveData.value = actualPhoneNumber.length == PHONE_NUMBER_LENGTH
    }

    override fun onCleared() {
        super.onCleared()

        authPhoneInteractor.clearSmsCodeStatus()
    }
}
