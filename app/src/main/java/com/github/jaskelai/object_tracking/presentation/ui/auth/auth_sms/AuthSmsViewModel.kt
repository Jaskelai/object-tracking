package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.domain.interactor.PhoneAuthInteractor
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.user_auth.UserAuthError
import com.github.jaskelai.object_tracking.domain.model.user_auth.UserAuthSuccess
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthSmsViewModel @Inject constructor(
    private val phoneAuthInteractor: PhoneAuthInteractor,
    private val resourceProvider: ResourceProvider
) : BaseViewModel() {

    val backNavigationLiveData = SingleEventLiveData<Boolean>(false)
    val toSetBioNavigationLiveData = SingleEventLiveData<Boolean>(false)
    val isVerifyButtonEnabledLiveData = MutableLiveData<Boolean>(false)

    var code: String = ""
        set(value) {
            field = value
            onCodeTyped()
        }

    companion object {
        private const val CODE_LENGTH = 6
    }

    fun onBackButtonClicked() {
        backNavigationLiveData.value = true
    }

    fun onVerifyBtnClicked() {
        verify()
    }

    private fun onCodeTyped() {
        isVerifyButtonEnabledLiveData.value =
            code.length == CODE_LENGTH && progressLiveData.value == false
    }

    private fun verify() {

        launch {
            progressLiveData.value = true

            phoneAuthInteractor.setCode(code)
            val result = phoneAuthInteractor.signIn()

            invalidateAfterRequest()

            handleResult(result)
        }
    }

    private fun handleResult(result: Result<UserAuthSuccess, UserAuthError>) {
        when (result) {
            is Result.Success -> {
                toSetBioNavigationLiveData.value = true
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

    private fun invalidateAfterRequest() {
        isVerifyButtonEnabledLiveData.value = true
        progressLiveData.value = false
    }
}
