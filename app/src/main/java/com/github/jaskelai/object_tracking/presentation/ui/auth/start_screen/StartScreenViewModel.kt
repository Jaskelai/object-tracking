package com.github.jaskelai.object_tracking.presentation.ui.auth.start_screen

import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import javax.inject.Inject

class StartScreenViewModel @Inject constructor() : BaseViewModel() {

    fun onSignInButtonClicked() = navigate(
        StartScreenFragmentDirections.actionStartScreenFragmentToAuthPhoneFragment()
    )
}
