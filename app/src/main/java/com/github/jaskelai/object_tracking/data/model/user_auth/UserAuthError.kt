package com.github.jaskelai.object_tracking.data.model.user_auth

import androidx.annotation.StringRes

data class UserAuthError(

    @StringRes val messageId: Int? = null,

    val message: String? = null
)
