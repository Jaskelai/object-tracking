package com.github.jaskelai.object_tracking.presentation.ui.main_flow.all_items

import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.domain.interactor.ItemInteractor
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.item.Item
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.common.adapter.ItemAdapter
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.common.adapter.ItemClickListener
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllItemsViewModel @Inject constructor(
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

    override fun omItemClick(item: Item) {

    }
}