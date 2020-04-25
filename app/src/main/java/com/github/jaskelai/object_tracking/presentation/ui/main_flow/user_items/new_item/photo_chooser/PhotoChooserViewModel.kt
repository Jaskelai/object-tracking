package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.domain.interactor.PhotoInteractor
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import java.io.File
import javax.inject.Inject

class PhotoChooserViewModel @Inject constructor(
    private val photoInteractor: PhotoInteractor
) : BaseViewModel() {

    private val _onNewPhotoClick = SingleEventLiveData(false)
    val onNewPhotoClick: LiveData<Boolean> = _onNewPhotoClick

    private val _onChooseFromGalleryClick = SingleEventLiveData(false)
    val onChooseFromGalleryClick: LiveData<Boolean> = _onChooseFromGalleryClick

    val currentFile = MutableLiveData<File>()

    fun onNewPhotoClick() {
        _onNewPhotoClick.value = true
    }

    fun onChooseFromGalleryClick() {
        _onChooseFromGalleryClick.value = true
    }

    fun onPhotoCaptured(reqCode: String) = navigate(
        NavigationCommand.BackWithResult(
            reqCode = reqCode,
            result = Uri.fromFile(currentFile.value!!)
        )
    )

    fun generatePhotoFile(): File {
        val file = photoInteractor.generateImage()
        currentFile.value = file
        return file
    }

    fun onPhotoFromGalleryChoosed(imageUri: Uri, reqCode: String) = navigate(
        NavigationCommand.BackWithResult(
            reqCode = reqCode,
            result = imageUri
        )
    )
}