package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item

import android.net.Uri
import androidx.lifecycle.LiveData
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.domain.interactor.PhotoInteractor
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field_manager.NewItemFieldManager
import com.github.jaskelai.object_tracking.presentation.utils.ext.generateRandomIntReq
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewItemViewModel @Inject constructor(
    val newItemFieldManager: NewItemFieldManager,

    private val photoInteractor: PhotoInteractor,
    private val resProvider: ResourceProvider
) : BaseViewModel() {

    val reqCodePhoto = generateRandomIntReq()

    fun onPhotoClick() = navigate(
        NavigationCommand.To(
            NewItemFragmentDirections.actionNewItemFragmentToChoosePhotoFragment(reqCodePhoto.toString())
        )
    )

    fun onSaveBtnClick() {

    }

    override fun onNavigationResult(value: Any) {
        value as Uri
        launch {
            newItemFieldManager.image.fieldValue.value = value
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