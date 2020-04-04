package com.github.jaskelai.object_tracking.data.local.db.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserDB (

    @PrimaryKey val id: String,

    val name: String,

    val surname: String
)