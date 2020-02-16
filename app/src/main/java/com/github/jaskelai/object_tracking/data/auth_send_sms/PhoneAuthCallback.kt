package com.github.jaskelai.object_tracking.data.auth_send_sms

import com.github.jaskelai.object_tracking.data.mapper.FirebaseErrorMapper
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.auth_send_sms.SendSmsError
import com.github.jaskelai.object_tracking.domain.model.auth_send_sms.SendSmsResult
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import javax.inject.Inject

@PerApp
class PhoneAuthCallback @Inject constructor(
    private val authRepository: AuthRepository,
    private val firebaseErrorMapper: FirebaseErrorMapper
) : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    override fun onVerificationCompleted(credential: PhoneAuthCredential) {
        authRepository.sendSmsStatusLiveData.value = SendSmsResult.Success
    }

    override fun onVerificationFailed(exception: FirebaseException) {
        authRepository.sendSmsStatusLiveData.value =
            SendSmsResult.Error(SendSmsError(firebaseErrorMapper.mapExceptionToResourceId(exception)))
    }

    override fun onCodeAutoRetrievalTimeOut(verificationId: String) {
        authRepository.sendSmsStatusLiveData.value = SendSmsResult.Timeout
    }

    override fun onCodeSent(verificationID: String, token: PhoneAuthProvider.ForceResendingToken) {
        authRepository.sendSmsStatusLiveData.value = SendSmsResult.CodeSent
    }
}
