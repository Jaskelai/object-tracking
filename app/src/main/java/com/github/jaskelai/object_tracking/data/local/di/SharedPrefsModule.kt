package com.github.jaskelai.object_tracking.data.local.di

import android.content.Context
import com.github.jaskelai.object_tracking.data.local.shared_prefs.SharedPrefsProvider
import com.github.jaskelai.object_tracking.data.local.shared_prefs.SharedPrefsProviderImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class SharedPrefsModule {

    @Module
    companion object {
        private const val NAME_SHARED_PREFS = "object_tracking_shared_prefs"

        @Provides
        @PerApp
        @JvmStatic
        fun provideSharedPrefs(context: Context) =
            context.getSharedPreferences(NAME_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    @Binds
    @PerApp
    abstract fun provideSharedPrefsProvider(sharedPrefsProviderImpl: SharedPrefsProviderImpl): SharedPrefsProvider
}
