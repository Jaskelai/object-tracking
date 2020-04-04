package com.github.jaskelai.object_tracking.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.jaskelai.object_tracking.data.local.db.user.dao.UserDao
import com.github.jaskelai.object_tracking.data.local.db.user.model.UserDB

@Database(entities = [UserDB::class], version = 1)
abstract class ObjectTrackingDB : RoomDatabase() {

    abstract fun userDAO(): UserDao
}