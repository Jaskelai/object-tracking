package com.github.jaskelai.object_tracking.data.network.di

import com.github.jaskelai.object_tracking.di.scope.PerApp
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
class NetCommonModule {

    @PerApp
    @Provides
    @LoggingQualifier
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @PerApp
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}

@Qualifier
annotation class LoggingQualifier