package com.github.jaskelai.object_tracking.presentation.ui.main_flow.item_details

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.presentation.base.BaseViewModel
import javax.inject.Inject

class ItemDetailsViewModel @Inject constructor(): BaseViewModel() {

    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val photoUrl = MutableLiveData<String>()
    val category = MutableLiveData<String>()

    private var id = ""

    fun receiveArgs(args: ItemDetailsFragmentArgs) {
        name.value = args.name
        description.value = args.description
        photoUrl.value = args.imageUrl
        category.value = args.category
    }
 }