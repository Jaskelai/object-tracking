package com.github.jaskelai.object_tracking.data.network.user.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserInfoNet (

    var name: String = "",
    var surname: String = ""
)