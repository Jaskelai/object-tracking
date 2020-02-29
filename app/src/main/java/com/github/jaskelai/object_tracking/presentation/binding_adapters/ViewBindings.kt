package com.github.jaskelai.object_tracking.presentation.binding_adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isVisible")
fun setVisibility(view: View, value: Boolean?) {
    when (value) {
        true -> view.visibility = View.VISIBLE
        false -> view.visibility = View.INVISIBLE
    }
}
