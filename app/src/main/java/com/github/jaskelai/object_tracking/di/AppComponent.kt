package com.github.jaskelai.object_tracking.di

import android.content.Context
import com.github.jaskelai.object_tracking.AppDelegate
import com.github.jaskelai.object_tracking.di.scope.PerApp
import dagger.BindsInstance
import dagger.Component

@PerApp
@Component(modules = [])
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun inject(appDelegate: AppDelegate)
}
