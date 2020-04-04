package com.github.jaskelai.object_tracking.data.network.di

import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    companion object {
        private const val LANGUAGE_CODE = "ru"
    }

    @Provides
    @PerApp
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance().apply {
            setLanguageCode(LANGUAGE_CODE)
        }
    }
}
