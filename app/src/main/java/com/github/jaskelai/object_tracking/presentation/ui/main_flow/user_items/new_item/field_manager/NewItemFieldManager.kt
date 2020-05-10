package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field_manager

import android.net.Uri
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.field.Field
import com.github.jaskelai.object_tracking.presentation.utils.ext.isAllTrue
import javax.inject.Inject

class NewItemFieldManager @Inject constructor() {

    private companion object {
        const val NAME_MIN_LENGTH = 3
    }

    val image = Field<Uri?>(::checkIsNull)
    val name = Field(String::isNullOrEmpty, ::validateName)
    val description = Field(String::isNullOrEmpty)

    val isFormValid = listOf(image.isValid, name.isValid, description.isValid, image.initialized, name.initialized, description.initialized)
        .isAllTrue()

    private fun validateName(value: String?): Boolean {

        return value != null && value.length >= NAME_MIN_LENGTH
    }

    private fun checkIsNull(any: Any?): Boolean = any == null

}
