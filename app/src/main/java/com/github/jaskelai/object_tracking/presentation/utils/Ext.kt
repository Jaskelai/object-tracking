package com.github.jaskelai.object_tracking.presentation.utils

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.AppDelegate
import com.github.jaskelai.object_tracking.di.AppComponent
import com.github.jaskelai.object_tracking.presentation.MainActivity

fun Activity.getAppComponent(): AppComponent = (application as AppDelegate).appComponent

fun Fragment.getMainActivity(): MainActivity = activity as MainActivity

fun <BINDING : ViewDataBinding> Fragment.bindData(
    inflater: LayoutInflater,
    container: ViewGroup?,
    @LayoutRes layoutId: Int
): BINDING {
    return DataBindingUtil.inflate<BINDING>(
        inflater,
        layoutId,
        container,
        false
    ).apply {
        lifecycleOwner = this@bindData
    }
}

fun <BINDING : ViewDataBinding> Fragment.bindDataAndAttachToRoot(
    inflater: LayoutInflater,
    container: ViewGroup?,
    @LayoutRes layoutId: Int
): BINDING {
    return DataBindingUtil.inflate<BINDING>(
        inflater,
        layoutId,
        container,
        true
    ).apply {
        lifecycleOwner = this@bindDataAndAttachToRoot
    }
}