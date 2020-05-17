package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item

import android.net.Uri
import androidx.lifecycle.MediatorLiveData
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.domain.interactor.ItemInteractor
import com.github.jaskelai.object_tracking.domain.interactor.PhotoInteractor
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.item.Item
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field_manager.NewItemFieldManager
import com.github.jaskelai.object_tracking.presentation.utils.ext.generateRandomIntReq
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewItemViewModel @Inject constructor(
    val fieldManager: NewItemFieldManager,

    private val newItemInteractor: ItemInteractor,
    private val photoInteractor: PhotoInteractor,
    private val resProvider: ResourceProvider
) : BaseViewModel() {

    val reqCodePhoto = generateRandomIntReq()

    val isBtnEnabled = MediatorLiveData<Boolean>().apply {
        addSource(fieldManager.isFormValid) { isFormValid -> this.value = isFormValid }
    }

    fun onPhotoClick() = navigate(
        NavigationCommand.To(
            NewItemFragmentDirections.actionNewItemFragmentToChoosePhotoFragment(reqCodePhoto.toString())
        )
    )

    fun onSaveBtnClick() {
        launch {
            isBtnEnabled.value = false
            val imageUrl = fieldManager.image.fieldValue.value?.let { uri ->
                photoInteractor.uploadImage(uri)
            }
            val result = newItemInteractor.addItem(Item(
                id = "",
                name = fieldManager.name.fieldValue.value ?: "",
                description = fieldManager.description.fieldValue.value,
                category = fieldManager.category.fieldValue.value ?: "",
                imageUrl = imageUrl
            ))
            when (result) {
                is Result.Success -> navigate(NavigationCommand.Back)
            }
        }.invokeOnCompletion { isBtnEnabled.value = true }
    }

    override fun onNavigationResult(value: Any) {
        value as Uri
        launch {
            fieldManager.image.fieldValue.value = value
            handleLabelingResult(photoInteractor.labelPhoto(value))
        }
    }

    private fun handleLabelingResult(result: Result<List<String>, ErrorModel>) {
        when (result) {
            is Result.Success -> {
                for (label in result.data!!) {
                }
            }
            is Result.Error -> {
                errorMessageLiveData.value =
                    resProvider.getString(result.data?.messageId ?: R.string.error_common)
            }
        }
    }
}