package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main

import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import javax.inject.Inject

class UserItemsViewModel @Inject constructor() : BaseViewModel() {

    fun onNewItemClick() = navigate(
        UserItemsFragmentDirections.actionUserItemsFragmentToNewItemFragment()
    )
}