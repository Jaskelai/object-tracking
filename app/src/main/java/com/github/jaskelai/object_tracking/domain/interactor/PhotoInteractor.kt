package com.github.jaskelai.object_tracking.domain.interactor

import android.net.Uri
import com.github.jaskelai.object_tracking.domain.interfaces.PhotoRepository
import com.github.jaskelai.object_tracking.domain.interfaces.TranslationRepository
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.label.PhotoLabel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.lang.Exception
import javax.inject.Inject

class PhotoInteractor @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val translationRepository: TranslationRepository
) {

    private companion object {
        const val LABEL_LANG_TRANSLATION = "en-ru"
    }

    fun generateImage(): File = photoRepository.generateFile()

    suspend fun labelPhoto(uri: Uri): Result<List<String>, ErrorModel> {

        val labelsResultClear = photoRepository.labelPhoto(uri)

        return if (labelsResultClear is Result.Success) {
            val resultOnlyName = labelsResultClear.data!!.map { it.name }

            val translatedText = try {
                withContext(Dispatchers.IO) {
                    Result.Success(
                        translationRepository.translate(
                            texts = resultOnlyName,
                            lang = LABEL_LANG_TRANSLATION
                        )
                    )
                }
            } catch (ex: Exception) {
                println(ex.message)
                Result.Success(resultOnlyName)
            }

            translatedText

        } else labelsResultClear as Result.Error
    }
}