package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.domain.interactor.PhoneAuthInteractor
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthSmsViewModel @Inject constructor(
    private val phoneAuthInteractor: PhoneAuthInteractor
) : BaseViewModel() {

    val backNavigationLiveData = SingleEventLiveData<Boolean>()
    val isVerifyButtonEnabledLiveData = MutableLiveData<Boolean>()

    var code: String = ""
        set(value) {
            field = value
            onCodeTyped()
        }

    companion object {
        private const val CODE_LENGTH = 6
    }

    init {
        backNavigationLiveData.value = false
        isVerifyButtonEnabledLiveData.value = false
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
            phoneAuthInteractor.signIn()
            invalidateAfterRequest()
        }
    }

    private fun invalidateAfterRequest() {
        isVerifyButtonEnabledLiveData.value = true
        progressLiveData.value = false
    }
}
