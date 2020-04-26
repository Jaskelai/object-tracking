package com.github.jaskelai.object_tracking.data.network.translate

import com.github.jaskelai.object_tracking.data.network.translate.api.YandexTranslationResultModel

interface NetworkTranslationDataSource {

    suspend fun translateText(text: String, lang: String): YandexTranslationResultModel
}