package com.github.jaskelai.object_tracking.domain.interfaces

import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.user_auth.AuthState
import com.google.firebase.auth.PhoneAuthCredential

interface AuthRepository {

    var verificationId: String?
    var code: String?
    var phoneAuthCredential: PhoneAuthCredential?

    suspend fun signIn(): Result<Unit, ErrorModel>

    fun getAuthState(): AuthState

    suspend fun setBio(userBio: UserBio): Result<Unit, ErrorModel>
}
