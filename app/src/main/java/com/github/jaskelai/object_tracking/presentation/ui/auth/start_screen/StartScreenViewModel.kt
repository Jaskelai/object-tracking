package com.github.jaskelai.object_tracking.presentation.ui.auth.start_screen

import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import javax.inject.Inject

class StartScreenViewModel @Inject constructor() : BaseViewModel() {

    fun onSignInButtonClicked() = navigate(
        NavigationCommand.To(
            StartScreenFragmentDirections.actionStartScreenFragmentToAuthPhoneFragment()
        )
    )
}
