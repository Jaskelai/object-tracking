package com.github.jaskelai.object_tracking.domain.interactor

import com.github.jaskelai.object_tracking.domain.interfaces.PhotoRepository
import java.io.File
import javax.inject.Inject

class PhotoInteractor @Inject constructor(private val photoRepository: PhotoRepository) {

    fun generateImage(): File = photoRepository.generateFile()
}