package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.mediator

import android.net.Uri
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelLifecycleOwner
import javax.inject.Inject

class NewItemFieldMediator @Inject constructor(

    viewModelLifecycleOwner: ViewModelLifecycleOwner

): LifecycleOwner by viewModelLifecycleOwner   {

    val image = MutableLiveData<Uri>()
}
