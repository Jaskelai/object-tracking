package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main

import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import javax.inject.Inject

class UserItemsViewModel @Inject constructor() : BaseViewModel() {

    fun onNewItemClick() = navigate(
        NavigationCommand.To(
            UserItemsFragmentDirections.actionUserItemsFragmentToNewItemFragment()
        )
    )
}