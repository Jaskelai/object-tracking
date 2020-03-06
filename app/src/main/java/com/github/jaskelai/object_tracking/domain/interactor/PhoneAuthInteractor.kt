package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.model.user_auth.UserAuthError
import com.github.jaskelai.object_tracking.domain.model.user_auth.UserAuthSuccess
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.common.Result
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

    suspend fun signIn(): Result<UserAuthSuccess, UserAuthError> = authRepository.signIn()

    private fun setCredential() {
        val code = authRepository.code
        val verificationId = authRepository.verificationId

        if (code != null && verificationId != null) {
            val credential = PhoneAuthProvider.getCredential(verificationId, code)
            authRepository.phoneAuthCredential = credential
        }
    }
}
