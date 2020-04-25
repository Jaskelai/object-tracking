package com.github.jaskelai.object_tracking.data.repository.photo

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.domain.interfaces.PhotoRepository
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val context: Context): PhotoRepository {

    private companion object {
        const val PATTERN_FORMAT_DATE = "yyyyMMdd_HHmmss"
    }

    override val currentImageFilePath = MutableLiveData<String>()

    override fun generateFile(): File {
        val timeStamp = SimpleDateFormat(PATTERN_FORMAT_DATE, Locale.getDefault()).format(Date())
        val imageFileName = "IMG_${timeStamp}_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageFile = File.createTempFile(imageFileName, ".jpg", storageDir)
        currentImageFilePath.value = imageFile.absolutePath
        return imageFile
    }
}