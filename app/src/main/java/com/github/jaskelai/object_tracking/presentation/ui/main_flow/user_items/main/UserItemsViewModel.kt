package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main

import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.domain.interactor.ItemInteractor
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.item.Item
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main.adapter.ItemAdapter
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.kpfu.itis.quailly.app.ui.new_item.category.adapter.ItemClickListener
import javax.inject.Inject

class UserItemsViewModel @Inject constructor(
    private val itemInteractor: ItemInteractor,
    private val resourceProvider: ResourceProvider
) : BaseViewModel(), ItemClickListener<Item> {

    val adapter = ItemAdapter(this)

    init {
        fetchItems()
    }

    private fun fetchItems() = launch {
        itemInteractor.getUserItems().collect { result ->
            when (result) {
                is Result.Success -> adapter.submitList(result.data)
                is Result.Error -> errorMessageLiveData.value = resourceProvider.getString(R.string.error_common)
            }
        }
    }

    fun onNewItemClick() = navigate(
        NavigationCommand.To(
            UserItemsFragmentDirections.actionUserItemsFragmentToNewItemFragment()
        )
    )

    override fun omItemClick(item: Item) {

    }
}