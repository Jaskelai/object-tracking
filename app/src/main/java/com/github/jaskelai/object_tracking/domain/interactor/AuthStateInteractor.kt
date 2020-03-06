package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import javax.inject.Inject

class AuthStateInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    fun isAuthed(): Boolean = authRepository.isAuthed()
}
