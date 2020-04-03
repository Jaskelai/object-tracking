package com.github.jaskelai.object_tracking.presentation

import androidx.lifecycle.LiveData
import com.github.jaskelai.object_tracking.domain.interactor.AuthStateInteractor
import com.github.jaskelai.object_tracking.domain.model.user_auth.AuthState
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authStateInteractor: AuthStateInteractor
) : BaseViewModel() {

    private val _isAuthed = SingleEventLiveData<AuthState>()
    val isAuthed: LiveData<AuthState> =  _isAuthed

    init {
        dealWithAuthState()
    }

    private fun dealWithAuthState() {
        _isAuthed.value = authStateInteractor.getAuthState()
    }
}
