package com.github.jaskelai.object_tracking.data.repository.item.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ItemNet (

    var name: String = "",
    var description: String? = "",
    val imageUrl: String? = "",
    val category: String= ""
)