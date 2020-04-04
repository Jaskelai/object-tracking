package com.github.jaskelai.object_tracking.data.network.user.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfoNet (

    val name: String,
    val surname: String
)