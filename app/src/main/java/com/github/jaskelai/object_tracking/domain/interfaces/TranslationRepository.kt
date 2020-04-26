package com.github.jaskelai.object_tracking.domain.interfaces

interface TranslationRepository {

    suspend fun translate(text: String, lang: String): String

    suspend fun translate(texts: List<String>, lang: String): List<String>
}