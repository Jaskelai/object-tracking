package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.bio.UserBio
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import javax.inject.Inject

class SetBioInteractor @Inject constructor(private val authRepository: AuthRepository) {

    suspend fun setBio(userBio: UserBio): Result<Unit, ErrorModel> = authRepository.setBio(userBio)
}