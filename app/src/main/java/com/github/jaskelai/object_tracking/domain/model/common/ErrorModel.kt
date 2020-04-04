package com.github.jaskelai.object_tracking.domain.model.common

import androidx.annotation.StringRes

data class ErrorModel(

    @StringRes val messageId: Int? = null,

    val message: String? = null
)
