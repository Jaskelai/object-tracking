package com.github.jaskelai.object_tracking.data.network.di

import com.github.jaskelai.object_tracking.BuildConfig
import com.github.jaskelai.object_tracking.data.network.translate.api.YandexTranslationApi
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
class YandexTranslateNetModule {

    private companion object {
        const val KEY_QUERY_NAME = "key"
    }

    @Provides
    @PerApp
    fun provideYandexTranslationApi(@YandexTranslateQualifier retrofit: Retrofit) =
        retrofit.create(YandexTranslationApi::class.java)

    @Provides
    @PerApp
    @YandexTranslateQualifier
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @YandexTranslateQualifier okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.YANDEX_TRANSLATE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @PerApp
    @YandexTranslateQualifier
    fun provideOkHttp(
        @LoggingQualifier loggingInterceptor: Interceptor,
        @YandexTranslateQualifier interceptor: Interceptor
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
    @YandexTranslateQualifier
    fun provideInterceptor(): Interceptor = Interceptor {
        val originalRequest = it.request()

        val url = originalRequest.url.newBuilder()
            .addQueryParameter(KEY_QUERY_NAME, BuildConfig.YANDEX_TRANSLATE_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(url)
            .build()

        it.proceed(newRequest)
    }
}

@Qualifier
annotation class YandexTranslateQualifier