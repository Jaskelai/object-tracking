package com.github.jaskelai.object_tracking.data.repository

import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.local.shared_prefs.SharedPrefsProvider
import com.github.jaskelai.object_tracking.data.mapper.FirebaseErrorMapper
import com.github.jaskelai.object_tracking.data.mapper.FirebaseUserAuthMapper
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.user_auth.UserAuthError
import com.github.jaskelai.object_tracking.domain.model.user_auth.UserAuthSuccess
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firebaseUserAuthMapper: FirebaseUserAuthMapper,
    private val firebaseErrorMapper: FirebaseErrorMapper,
    private val sharedPrefsProvider: SharedPrefsProvider
) : AuthRepository {

    override var verificationId: String? = ""
    override var code: String? = ""
    override var phoneAuthCredential: PhoneAuthCredential? = null

    companion object {
        private const val KEY_IS_AUTHED = "is_authed"
    }

    override suspend fun signIn(): Result<UserAuthSuccess, UserAuthError> =
        suspendCoroutine { cont ->
            phoneAuthCredential?.let { credential ->

                auth.signInWithCredential(credential)
                    .addOnSuccessListener { authResult ->
                        val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false

                        sharedPrefsProvider.writeBoolean(KEY_IS_AUTHED, true)

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

    override fun isAuthed(): Boolean = sharedPrefsProvider.readBoolean(KEY_IS_AUTHED)
}
