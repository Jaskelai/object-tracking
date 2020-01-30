package com.github.jaskelai.object_tracking

import android.app.Application
import com.github.jaskelai.object_tracking.di.AppComponent
import com.github.jaskelai.object_tracking.di.DaggerAppComponent

class AppDelegate : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory()
            .create(this)
        appComponent.inject(this)
    }
}
