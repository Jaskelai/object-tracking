package com.github.jaskelai.object_tracking.data.network.translate.api

import com.google.gson.annotations.SerializedName

data class YandexTranslationResultModel (

    @SerializedName("code") val code: Int,

    @SerializedName("lang") val lang: String,

    @SerializedName("text") val texts: List<String>

)