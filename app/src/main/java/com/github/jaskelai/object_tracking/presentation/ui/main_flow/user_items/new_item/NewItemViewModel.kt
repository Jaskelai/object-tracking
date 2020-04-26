package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item

import android.net.Uri
import com.github.jaskelai.object_tracking.R
import com.github.jaskelai.object_tracking.domain.interactor.PhotoInteractor
import com.github.jaskelai.object_tracking.domain.model.common.ErrorModel
import com.github.jaskelai.object_tracking.domain.model.common.Result
import com.github.jaskelai.object_tracking.domain.model.label.PhotoLabel
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.mediator.NewItemFieldMediator
import com.github.jaskelai.object_tracking.presentation.utils.ext.generateRandomIntReq
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewItemViewModel @Inject constructor(
    val newItemFieldMediator: NewItemFieldMediator,

    private val photoInteractor: PhotoInteractor,
    private val resProvider: ResourceProvider
) : BaseViewModel() {

    val reqCodePhoto = generateRandomIntReq()

    fun onPhotoClick() = navigate(
        NavigationCommand.To(
            NewItemFragmentDirections.actionNewItemFragmentToChoosePhotoFragment(reqCodePhoto.toString())
        )
    )

    override fun onNavigationResult(value: Any) {
        value as Uri
        launch {
            newItemFieldMediator.image.value = value
            handleLabelingResult(photoInteractor.labelPhoto(value))
        }
    }

    private fun handleLabelingResult(result: Result<List<String>, ErrorModel>) {
        when (result) {
            is Result.Success -> {
                for (label in result.data!!) {
                    println(label)
                }
            }
            is Result.Error -> {
                errorMessageLiveData.value =
                    resProvider.getString(result.data?.messageId ?: R.string.error_common)
            }
        }
    }
}