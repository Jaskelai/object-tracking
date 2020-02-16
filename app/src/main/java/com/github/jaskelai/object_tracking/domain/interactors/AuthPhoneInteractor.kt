package com.github.jaskelai.object_tracking.domain.interactors

import androidx.lifecycle.LiveData
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.auth_send_sms.SendSmsResult
import javax.inject.Inject

class AuthPhoneInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun observeSendSmsCodeStatus(): LiveData<SendSmsResult> = authRepository.sendSmsStatusLiveData

    fun clearSmsCodeStatus() {
        authRepository.sendSmsStatusLiveData.value = null
    }
}
