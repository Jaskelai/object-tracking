package com.github.jaskelai.object_tracking.data.local.db.user.dao

import androidx.room.*
import com.github.jaskelai.object_tracking.data.local.db.user.model.UserDB
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUserAsync(): Flow<UserDB>

    @Query("SELECT * FROM user")
    fun getUserSync(): UserDB

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(userDB: UserDB)

    @Delete()
    fun deleteUser(userDB: UserDB)
}
