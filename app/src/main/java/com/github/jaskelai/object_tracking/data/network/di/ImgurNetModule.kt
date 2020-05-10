package com.github.jaskelai.object_tracking.data.network.di

import com.github.jaskelai.object_tracking.BuildConfig
import com.github.jaskelai.object_tracking.data.network.image.api.ImageUploadImgurApi
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
class ImgurNetModule {

    private companion object {
        const val HEADER_ACCESS_TOKEN = "Authorization"
    }

    @Provides
    @PerApp
    fun provideImgurUploadingApi(@ImgurQualifier retrofit: Retrofit) =
        retrofit.create(ImageUploadImgurApi::class.java)

    @Provides
    @PerApp
    @ImgurQualifier
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        @ImgurQualifier okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.IMGUR_UPLOAD_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Provides
    @PerApp
    @ImgurQualifier
    fun provideOkHttp(
        @ImgurQualifier interceptor: Interceptor,
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
    @ImgurQualifier
    fun provideInterceptor(): Interceptor = Interceptor {
        var request = it.request().newBuilder()
            .addHeader(HEADER_ACCESS_TOKEN, "Client-ID ${BuildConfig.IMGUR_CLIENT_ID}")
            .build()

        it.proceed(request)
    }
}

@Qualifier
annotation class ImgurQualifier