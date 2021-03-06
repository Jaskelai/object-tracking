package com.github.jaskelai.object_tracking.data.repository.user

import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.local.db.user.DBUserDataSource
import com.github.jaskelai.object_tracking.data.local.db.user.model.UserDB
import com.github.jaskelai.object_tracking.data.local.shared_prefs.SharedPrefsProvider
import com.github.jaskelai.object_tracking.data.network.user.NetworkUserDataSource
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.user_auth.AuthState
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val sharedPrefsProvider: SharedPrefsProvider,
    private val networkUserDataSource: NetworkUserDataSource,
    private val dbUserDataSource: DBUserDataSource
) : AuthRepository {

    override var verificationId: String? = ""
    override var code: String? = ""
    override var phoneAuthCredential: PhoneAuthCredential? = null

    companion object {
        private const val KEY_AUTH_STATE = "auth_state"
    }

    override suspend fun signIn(): Result<Unit, ErrorModel> = phoneAuthCredential?.let {
        val result = networkUserDataSource.signIn(it)

        when (result) {
            is Result.Success -> {
                setAuthState(AuthState.AUTHED_WITH_SMS)
            }
        }
        result
    } ?: Result.Error(ErrorModel(messageId = R.string.error_common))

    override fun setAuthState(authState: AuthState) = sharedPrefsProvider.writeString(
            key = KEY_AUTH_STATE,
            value = authState.name
        )

    override fun getAuthState(): AuthState = AuthState.valueOf(
        sharedPrefsProvider.readString(KEY_AUTH_STATE) ?: AuthState.NOT_AUTHED.name
    )

    override fun getUserId(): String = auth.currentUser?.uid ?: ""

    override suspend fun setBio(userBio: UserBio): Result<Unit, ErrorModel> = withContext(Dispatchers.IO) {
        auth.currentUser?.uid?.let {
            val result = networkUserDataSource.setBio(userBio, it)

            when (result) {
                is Result.Success -> {
                    dbUserDataSource.saveUser(UserDB(
                        id = it,
                        name = userBio.name,
                        surname = userBio.surname
                    ))
                    setAuthState(AuthState.FULL_AUTHED)
                }
            }
            result
        } ?: Result.Error(ErrorModel(messageId = R.string.error_common))
    }
}
