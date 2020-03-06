package com.github.jaskelai.object_tracking.presentation.utils.resource_provider

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringId: Int): String
}