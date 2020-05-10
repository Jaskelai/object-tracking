package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field

import androidx.lifecycle.MutableLiveData
import com.github.jaskelai.object_tracking.presentation.utils.ext.map
import com.github.jaskelai.object_tracking.presentation.utils.ext.startWith

class Field<T : Any?>(
    isEmptyValidation: ((T) -> Boolean),
    validationAction: ((T) -> Boolean)? = null
) {

    val fieldValue = MutableLiveData<T>()

    val initialized = if (validationAction == null) {
        MutableLiveData(true)
    } else {
        fieldValue.map { value -> !isEmptyValidation.invoke(value) }
    }

    val isValid = fieldValue
        .map {
            validationAction?.invoke(it) ?: true
        }
        .startWith(true)

}