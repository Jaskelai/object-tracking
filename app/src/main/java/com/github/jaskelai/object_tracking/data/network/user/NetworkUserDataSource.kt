package com.github.jaskelai.object_tracking.data.network.user

import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.google.firebase.auth.PhoneAuthCredential

interface NetworkUserDataSource {

    suspend fun signIn(credential: PhoneAuthCredential): Result<Unit, ErrorModel>

    suspend fun setBio(userBio: UserBio, userId: String): Result<Unit, ErrorModel>
}