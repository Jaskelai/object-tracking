package com.github.jaskelai.object_tracking.domain.interfaces

import android.net.Uri
import androidx.lifecycle.LiveData
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.label.PhotoLabel
import java.io.File

interface PhotoRepository {

    val currentImageFilePath: LiveData<String>

    fun generateFile(): File

    suspend fun labelPhoto(uri: Uri): Result<List<PhotoLabel>, ErrorModel>
}