package com.github.jaskelai.object_tracking.presentation

import com.github.jaskelai.object_tracking.domain.interactor.AuthStateInteractor
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val authStateInteractor: AuthStateInteractor
) : BaseViewModel() {

    val isAuthed = SingleEventLiveData<Boolean>()

    init {
        dealWithAuthState()
    }

    private fun dealWithAuthState() {
        isAuthed.value = authStateInteractor.isAuthed()
    }
}
