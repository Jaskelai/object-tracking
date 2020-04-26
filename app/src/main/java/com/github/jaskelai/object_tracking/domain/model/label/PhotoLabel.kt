package com.github.jaskelai.object_tracking.domain.model.label

data class PhotoLabel (
    val name: String,
    val entityId: String?,
    val confidence: Float
)
