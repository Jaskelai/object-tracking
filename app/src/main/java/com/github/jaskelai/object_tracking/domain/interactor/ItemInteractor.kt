package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.ItemRepository
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.item.Item
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ItemInteractor @Inject constructor(private val itemRepository: ItemRepository) {

    suspend fun addItem(item: Item): Result<Unit, ErrorModel> {
        return itemRepository.addItem(item)
    }

    suspend fun getUserItems(): Flow<Result<List<Item>, ErrorModel>> {
        return itemRepository.getUserItems()
    }
}