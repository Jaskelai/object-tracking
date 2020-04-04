package com.github.jaskelai.object_tracking.data.local.di

import android.content.Context
import androidx.room.Room
import com.github.jaskelai.object_tracking.data.local.db.ObjectTrackingDB
import com.github.jaskelai.object_tracking.data.local.db.user.DBUserDataSource
import com.github.jaskelai.object_tracking.data.local.db.user.DBUserDataSourceImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RoomDBModule {

    @Module
    companion object {
        private const val DATABASE_NAME = "object_tracking_db.db"

        @Provides
        @PerApp
        @JvmStatic
        fun provideTrackDB(appContext: Context): ObjectTrackingDB =
            Room.databaseBuilder(appContext, ObjectTrackingDB::class.java, DATABASE_NAME)
                .build()

        @Provides
        @PerApp
        @JvmStatic
        fun provideUserDao(objectTrackingDB: ObjectTrackingDB) = objectTrackingDB.userDAO()
    }

    @Binds
    @PerApp
    abstract fun bind1(impl: DBUserDataSourceImpl): DBUserDataSource
}