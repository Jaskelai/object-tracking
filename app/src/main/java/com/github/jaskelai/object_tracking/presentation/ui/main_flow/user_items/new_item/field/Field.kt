package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ext.map
import com.github.jaskelai.object_tracking.presentation.utils.ext.startWith

class Field<T : Any?>(validationAction: ((T) -> Boolean)? = null) {

    val fieldValue = MutableLiveData<T>()

    val isValid = fieldValue
        .map { validationAction?.invoke(it) ?: true }
        .startWith(true)

}