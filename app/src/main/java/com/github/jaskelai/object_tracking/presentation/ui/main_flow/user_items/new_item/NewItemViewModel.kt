package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item

import android.net.Uri
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import com.github.jaskelai.object_tracking.presentation.navigation.NavigationCommand
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.mediator.NewItemFieldMediator
import com.github.jaskelai.object_tracking.presentation.utils.ext.generateRandomIntReq
import javax.inject.Inject

class NewItemViewModel @Inject constructor(
    val newItemFieldMediator: NewItemFieldMediator
) : BaseViewModel() {

    val reqCodePhoto = generateRandomIntReq()

    fun onPhotoClick() = navigate(
        NavigationCommand.To(
            NewItemFragmentDirections.actionNewItemFragmentToChoosePhotoFragment(reqCodePhoto.toString())
        )
    )

    override fun onNavigationResult(value: Any) {
        newItemFieldMediator.image.value = value as Uri
    }
}