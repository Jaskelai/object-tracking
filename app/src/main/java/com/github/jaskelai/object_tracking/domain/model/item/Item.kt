package com.github.jaskelai.object_tracking.domain.model.item

data class Item (
    val name: String,
    val description: String?,
    val category: String,
    val imageUrl: String?
)