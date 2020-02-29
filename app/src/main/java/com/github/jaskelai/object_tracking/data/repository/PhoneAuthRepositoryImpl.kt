package com.github.jaskelai.object_tracking.data.repository

import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.mapper.FirebaseErrorMapper
import com.github.jaskelai.object_tracking.data.mapper.FirebaseUserAuthMapper
import com.github.jaskelai.object_tracking.data.model.user_auth.UserAuthError
import com.github.jaskelai.object_tracking.data.model.user_auth.UserAuthSuccess
import com.github.jaskelai.object_tracking.domain.interfaces.PhoneAuthRepository
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PhoneAuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firebaseUserAuthMapper: FirebaseUserAuthMapper,
    private val firebaseErrorMapper: FirebaseErrorMapper
) : PhoneAuthRepository {

    override var verificationId: String? = ""
    override var code: String? = ""
    override var phoneAuthCredential: PhoneAuthCredential? = null

    override suspend fun signIn(): Result<UserAuthSuccess, UserAuthError> =
        suspendCoroutine { cont ->
            phoneAuthCredential?.let { credential ->

                auth.signInWithCredential(credential)
                    .addOnSuccessListener { authResult ->
                        val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
                        cont.resume(
                            Result.Success(
                                firebaseUserAuthMapper.mapFirebaseUserToUserAuth(
                                    authResult.user!!,
                                    isNewUser
                                )
                            )
                        )
                    }
                    .addOnCanceledListener {
                        cont.resume(
                            Result.Error(
                                UserAuthError(messageId = R.string.error_canceled)
                            )
                        )
                    }
                    .addOnFailureListener {
                        when (it) {
                            is FirebaseAuthInvalidCredentialsException -> {
                                cont.resume(Result.Error(UserAuthError(message = it.localizedMessage)))
                            }
                            else -> {
                                cont.resume(
                                    Result.Error(
                                        UserAuthError(
                                            messageId = firebaseErrorMapper.mapExceptionToResourceId(
                                                it as FirebaseException
                                            )
                                        )
                                    )
                                )
                            }
                        }
                    }
            } ?: cont.resume(Result.Error(UserAuthError(messageId = R.string.error_common)))
        }
}
