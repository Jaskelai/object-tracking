package com.github.jaskelai.object_tracking.presentation.navigation

import androidx.navigation.NavDirections

sealed class NavigationCommand {

    data class To(val directions: NavDirections): NavigationCommand()

    object Back: NavigationCommand()

    class BackWithResult(val reqCode: String, val result: Any): NavigationCommand()
}