package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.user_auth.AuthState
import javax.inject.Inject

class SetBioInteractor @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun setBio(userBio: UserBio): Result<Unit, ErrorModel> {
        val result = authRepository.setBio(userBio)

        when (result) {
            is Result.Success -> authRepository.setAuthState(AuthState.FULL_AUTHED)
        }

        return result
    }
}