package com.github.jaskelai.object_tracking.data.local.db.user

import com.github.jaskelai.object_tracking.data.local.db.user.model.UserDB

interface DBUserDataSource {

    suspend fun saveUser(userDB: UserDB)
}