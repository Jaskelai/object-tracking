package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.user_auth.AuthState
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

class PhoneAuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun setVerificationId(verificationId: String) {
        authRepository.verificationId = verificationId
    }

    fun setCode(code: String) {
        authRepository.code = code
        setCredential()
    }

    fun setCredentialViaObject(credential: PhoneAuthCredential) {
        authRepository.phoneAuthCredential = credential
    }

    suspend fun signIn(needSms: Boolean): Result<Unit, ErrorModel> {
        val result = authRepository.signIn()

        when (result) {
            is Result.Success -> {

                when (needSms) {
                    true -> authRepository.setAuthState(AuthState.AUTHED_WITH_SMS)
                    false -> authRepository.setAuthState(AuthState.FULL_AUTHED)
                }
            }
        }

        return result
    }

    private fun setCredential() {
        val code = authRepository.code
        val verificationId = authRepository.verificationId

        if (code != null && verificationId != null) {
            val credential = PhoneAuthProvider.getCredential(verificationId, code)
            authRepository.phoneAuthCredential = credential
        }
    }
}
