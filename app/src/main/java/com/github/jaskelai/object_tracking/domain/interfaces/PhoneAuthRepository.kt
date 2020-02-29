package com.github.jaskelai.object_tracking.domain.interfaces

import com.github.jaskelai.object_tracking.data.model.user_auth.UserAuthError
import com.github.jaskelai.object_tracking.data.model.user_auth.UserAuthSuccess
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.google.firebase.auth.PhoneAuthCredential

interface PhoneAuthRepository {

    var verificationId: String?
    var code: String?
    var phoneAuthCredential: PhoneAuthCredential?

    suspend fun signIn(): Result<UserAuthSuccess, UserAuthError>
}
