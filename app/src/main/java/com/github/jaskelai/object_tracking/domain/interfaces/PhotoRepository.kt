package com.github.jaskelai.object_tracking.domain.interfaces

import androidx.lifecycle.LiveData
import java.io.File

interface PhotoRepository {

    val currentImageFilePath: LiveData<String>

    fun generateFile(): File
}