package com.github.jaskelai.object_tracking.presentation.binding_adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun setPhoneMask(imageView: ImageView, url: String?) {
     if (url.isNullOrEmpty().not()) {
         Glide.with(imageView).load(url).into(imageView)
     }
}