package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser

import android.net.Uri
import androidx.lifecycle.LiveData
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.utils.SingleEventLiveData
import javax.inject.Inject

class PhotoChooserViewModel @Inject constructor() : BaseViewModel() {

    private val _onChooseFromGalleryClick = SingleEventLiveData(false)
    val onChooseFromGalleryClick: LiveData<Boolean> = _onChooseFromGalleryClick

    fun onNewPhotoClick() {

    }

    fun onChooseFromGalleryClick() {
        _onChooseFromGalleryClick.value = true
    }

    fun onPhotoChoosed(imageUri: Uri, reqCode: String) = navigate(
        NavigationCommand.BackWithResult(
            reqCode = reqCode,
            result = imageUri
        )
    )
}