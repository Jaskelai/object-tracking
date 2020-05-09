package com.github.jaskelai.object_tracking.data.network.di

import com.github.jaskelai.object_tracking.BuildConfig
import com.github.jaskelai.object_tracking.di.scope.PerApp
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Module
class GyazoNetModule {

    private companion object {
        const val HEADER_ACCESS_TOKEN = "Authorization"
    }

    @Provides
    @PerApp
    @GyazoQualifier
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @GyazoQualifier okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.GYAZO_UPLOAD_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @PerApp
    @GyazoQualifier
    fun provideOkHttp(
        @GyazoQualifier interceptor: Interceptor,
        @LoggingQualifier loggingInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)

        if (BuildConfig.DEBUG) builder.addInterceptor(loggingInterceptor)

        return builder.build()
    }

    @Provides
    @PerApp
    @GyazoQualifier
    fun provideInterceptor(): Interceptor = Interceptor {
        var request = it.request().newBuilder()
            .addHeader(HEADER_ACCESS_TOKEN, "Bearer ${BuildConfig.GYAZO_ACCESS_TOKEN}")
            .build()

        it.proceed(request)
    }
}

@Qualifier
annotation class GyazoQualifier