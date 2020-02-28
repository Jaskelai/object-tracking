package com.github.jaskelai.object_tracking.presentation.binding_adapters

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData

@BindingAdapter("isVisible")
fun setVisibility(view: View, value: LiveData<Boolean>) {
    when (value.value) {
        true -> view.visibility = View.VISIBLE
        else -> view.visibility = View.INVISIBLE
    }
}
