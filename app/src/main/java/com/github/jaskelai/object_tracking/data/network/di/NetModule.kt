package com.github.jaskelai.object_tracking.data.network.di

import com.github.jaskelai.object_tracking.data.network.user.NetworkUserDataSource
import com.github.jaskelai.object_tracking.data.network.user.NetworkUserDataSourceImpl
import com.github.jaskelai.object_tracking.data.network.translate.NetworkTranslationDataSource
import com.github.jaskelai.object_tracking.data.network.translate.NetworkTranslationDataSourceImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier

@Module
abstract class NetModule {

    @Module
    companion object {

        private const val USERS_DB_PATH = "users"

        @PerApp
        @Provides
        @JvmStatic
        fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

        @PerApp
        @Provides
        @JvmStatic
        @UserDBFirebaseQualifier
        fun provideFirebaseUserDatabase(db: FirebaseDatabase): DatabaseReference = db.getReference(
            USERS_DB_PATH
        )
    }

    @PerApp
    @Binds
    abstract fun bind1(impl: NetworkUserDataSourceImpl): NetworkUserDataSource

    @PerApp
    @Binds
    abstract fun bind2(impl: NetworkTranslationDataSourceImpl): NetworkTranslationDataSource
}

@Qualifier
annotation class UserDBFirebaseQualifier