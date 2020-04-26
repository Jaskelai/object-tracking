package com.github.jaskelai.object_tracking.data.repository.photo

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.data.mapper.FirebaseVisionImageLabelMapper
import com.github.jaskelai.object_tracking.domain.interfaces.PhotoRepository
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.label.PhotoLabel
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PhotoRepositoryImpl @Inject constructor(
    private val context: Context,
    private val firebaseVisionImageLabelMapper: FirebaseVisionImageLabelMapper
) : PhotoRepository {

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

    override suspend fun labelPhoto(uri: Uri): Result<List<PhotoLabel>, ErrorModel> =
        suspendCoroutine { cont ->
            val image = try {
                FirebaseVisionImage.fromFilePath(context, uri)
            } catch (ex: IOException) {
                null
            }
            val labeler = FirebaseVision.getInstance().onDeviceImageLabeler
            image?.let {
                labeler.processImage(image)
                    .addOnSuccessListener {
                        cont.resume(Result.Success(it.map { label ->
                            firebaseVisionImageLabelMapper.execute(label)
                        }))
                    }
                    .addOnFailureListener {
                        cont.resume(Result.Error(ErrorModel(R.string.not_recognized_choose_photo)))
                    }
            } ?: cont.resume(Result.Error(ErrorModel(R.string.photo_not_available_choose_photo)))
        }
}