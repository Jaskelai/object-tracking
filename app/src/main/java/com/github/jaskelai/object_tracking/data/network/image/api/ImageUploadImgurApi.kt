package com.github.jaskelai.object_tracking.data.network.image.api

import com.github.jaskelai.object_tracking.data.network.image.model.ImageUploadImgurResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface ImageUploadImgurApi {

    @POST("upload")
    suspend fun uploadImage(@Body image: RequestBody): ImageUploadImgurResponse
}