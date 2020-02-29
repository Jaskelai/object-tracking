package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.PhoneAuthRepository
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

class PhoneAuthInteractor @Inject constructor(
    private val phoneAuthRepository: PhoneAuthRepository
) {

    fun setVerificationId(verificationId: String) {
        phoneAuthRepository.verificationId = verificationId
    }

    fun setCode(code: String) {
        phoneAuthRepository.code = code
        setCredential()
    }

    fun setCredentialViaObject(credential: PhoneAuthCredential) {
        phoneAuthRepository.phoneAuthCredential = credential
    }

    suspend fun signIn() {
        phoneAuthRepository.signIn()
    }

    private fun setCredential() {
        val code = phoneAuthRepository.code
        val verificationId = phoneAuthRepository.verificationId

        if (code != null && verificationId != null) {
            val credential = PhoneAuthProvider.getCredential(verificationId, code)
            phoneAuthRepository.phoneAuthCredential = credential
        }
    }
}
