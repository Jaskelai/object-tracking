package com.github.jaskelai.object_tracking.data.local.db.user

import com.github.jaskelai.object_tracking.data.local.db.user.dao.UserDao
import com.github.jaskelai.object_tracking.data.local.db.user.model.UserDB
import javax.inject.Inject

class DBUserDataSourceImpl @Inject constructor(
    private val userDao: UserDao
): DBUserDataSource {

    override suspend fun saveUser(userDB: UserDB) {
        userDao.saveUser(userDB)
    }
}