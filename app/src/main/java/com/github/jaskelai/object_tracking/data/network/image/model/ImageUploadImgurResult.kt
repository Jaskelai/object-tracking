package com.github.jaskelai.object_tracking.data.network.image.model

import com.google.gson.annotations.SerializedName

data class ImageUploadImgurResult (

    @SerializedName("id") val id: String,
    @SerializedName("link") val link: String
)