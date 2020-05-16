package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main

import com.github.jaskelai.object_tracking.domain.model.item.Item
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main.adapter.ItemAdapter
import ru.kpfu.itis.quailly.app.ui.new_item.category.adapter.ItemClickListener
import javax.inject.Inject

class UserItemsViewModel @Inject constructor() : BaseViewModel(), ItemClickListener<Item> {

    val adapter = ItemAdapter(this)

    fun onNewItemClick() = navigate(
        NavigationCommand.To(
            UserItemsFragmentDirections.actionUserItemsFragmentToNewItemFragment()
        )
    )

    override fun omItemClick(item: Item) {

    }
}