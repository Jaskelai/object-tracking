package com.github.jaskelai.object_tracking.domain.interfaces

import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.item.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun addItem(item: Item): Result<Unit, ErrorModel>

    suspend fun getUserItems(): Flow<Result<List<Item>, ErrorModel>>

    suspend fun getAllItems(): Flow<Result<List<Item>, ErrorModel>>
}