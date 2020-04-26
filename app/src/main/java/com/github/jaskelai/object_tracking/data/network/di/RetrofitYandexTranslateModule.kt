package com.github.jaskelai.object_tracking.data.network.di

import com.github.jaskelai.object_tracking.BuildConfig
import com.github.jaskelai.object_tracking.data.network.translate.api.YandexTranslationApi
import com.github.jaskelai.object_tracking.di.scope.PerApp
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class RetrofitYandexTranslateModule {

    private companion object {
        const val KEY_QUERY_NAME = "key"
    }

    @Provides
    @PerApp
    fun provideYandexTranslationApi(retrofit: Retrofit) =
        retrofit.create(YandexTranslationApi::class.java)

    @Provides
    @PerApp
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.YANDEX_TRANSLATE_URL)
        .client(getOkHttp())
        .addConverterFactory(getGsonConverterFactory())
        .build()

    private fun getOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(getInterceptor())

        if (BuildConfig.DEBUG) builder.addInterceptor(getLoggingInterceptor())

        return builder.build()
    }

    private fun getInterceptor(): Interceptor = Interceptor {
        val originalRequest = it.request()

        val url = originalRequest.url.newBuilder()
            .addQueryParameter(KEY_QUERY_NAME, BuildConfig.YANDEX_TRANSLATE_KEY)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(url)
            .build()

        it.proceed(newRequest)
    }

    private fun getLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

    private fun getGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()
}