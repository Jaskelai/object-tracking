package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.model.user_auth.AuthState
import javax.inject.Inject

class AuthStateInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun getAuthState(): AuthState = authRepository.getAuthState()
}
