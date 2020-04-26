package com.github.jaskelai.object_tracking.data.network.translate

import com.github.jaskelai.object_tracking.data.network.translate.api.YandexTranslationApi
import com.github.jaskelai.object_tracking.data.network.translate.api.YandexTranslationResultModel
import javax.inject.Inject

class NetworkTranslationDataSourceImpl @Inject constructor(
    private val yandexTranslationApi: YandexTranslationApi
) : NetworkTranslationDataSource {

    override suspend fun translateText(text: String, lang: String): YandexTranslationResultModel {
        return yandexTranslationApi.getTranslatedText(
            text = text,
            lang = lang
        )
    }
}