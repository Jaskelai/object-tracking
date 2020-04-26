package com.github.jaskelai.object_tracking.domain.interactor

import android.net.Uri
import com.github.jaskelai.object_tracking.domain.interfaces.PhotoRepository
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.label.PhotoLabel
import java.io.File
import javax.inject.Inject

class PhotoInteractor @Inject constructor(private val photoRepository: PhotoRepository) {

    fun generateImage(): File = photoRepository.generateFile()

    suspend fun labelPhoto(uri: Uri): Result<List<PhotoLabel>, ErrorModel> = photoRepository.labelPhoto(uri)
}