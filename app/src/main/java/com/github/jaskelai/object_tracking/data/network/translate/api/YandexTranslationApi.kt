package com.github.jaskelai.object_tracking.data.network.translate.api

import retrofit2.http.POST
import retrofit2.http.Query

interface YandexTranslationApi {

    @POST("translate")
    suspend fun getTranslatedText(
        @Query("text") text: String,
        @Query("lang") lang: String
    ): YandexTranslationResultModel
}