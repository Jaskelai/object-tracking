package com.github.jaskelai.object_tracking.presentation.utils.ext

import android.app.Activity
import androidx.fragment.app.Fragment
import com.github.jaskelai.object_tracking.AppDelegate
import com.github.jaskelai.object_tracking.di.AppComponent
import com.github.jaskelai.object_tracking.presentation.MainActivity

fun Activity.getAppComponent(): AppComponent = (application as AppDelegate).appComponent

fun Fragment.getMainActivity(): MainActivity = activity as MainActivity
