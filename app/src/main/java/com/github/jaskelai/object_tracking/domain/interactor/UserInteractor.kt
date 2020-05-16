package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun getCurrentUserId(): String = authRepository.getUserId()
}