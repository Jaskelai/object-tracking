package com.github.jaskelai.object_tracking.data.repository.translation

import com.github.jaskelai.object_tracking.data.network.translate.NetworkTranslationDataSource
import com.github.jaskelai.object_tracking.domain.interfaces.TranslationRepository
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val networkTranslationDataSource: NetworkTranslationDataSource
) : TranslationRepository {

    override suspend fun translate(text: String, lang: String): String {
        return networkTranslationDataSource.translateText(
            text = text,
            lang = lang
        ).texts.first()
    }

    override suspend fun translate(texts: List<String>, lang: String): List<String> {
        val textsQuery = texts.joinToString(separator = ",")

        return networkTranslationDataSource.translateText(
            text = textsQuery,
            lang = lang
        ).texts.first().split(",")
    }
}