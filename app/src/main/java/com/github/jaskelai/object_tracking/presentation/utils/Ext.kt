package com.github.jaskelai.object_tracking.presentation.utils

import android.app.Activity
import com.github.jaskelai.object_tracking.AppDelegate
import com.github.jaskelai.object_tracking.di.AppComponent

fun Activity.getAppComponent(): AppComponent = (application as AppDelegate).appComponent
