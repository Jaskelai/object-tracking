package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field_manager

import android.net.Uri
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field.Field
import com.github.jaskelai.object_tracking.presentation.utils.ext.isAllTrue
import com.github.jaskelai.object_tracking.presentation.utils.ext.map
import com.github.jaskelai.object_tracking.presentation.utils.ext.skip
import com.github.jaskelai.object_tracking.presentation.utils.ext.startWith
import javax.inject.Inject

class NewItemFieldManager @Inject constructor() {

    private companion object {
        const val NAME_MIN_LENGTH = 3
    }

    val image = Field<Uri?>()
    val name = Field(::validateName)
    val description = Field<String?>()
    val isFormValid = listOf(image.isValid, name.isValid, description.isValid)
        .isAllTrue()
        .skip(3)

    private fun validateName(value: String?): Boolean {
        return value != null && value.length >= NAME_MIN_LENGTH
    }

}
