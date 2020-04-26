package com.github.jaskelai.object_tracking.di

import android.content.Context
import com.github.jaskelai.object_tracking.AppDelegate
import com.github.jaskelai.object_tracking.data.network.di.AuthModule
import com.github.jaskelai.object_tracking.data.network.di.NetModule
import com.github.jaskelai.object_tracking.data.di.RepositoryModule
import com.github.jaskelai.object_tracking.data.local.di.RoomDBModule
import com.github.jaskelai.object_tracking.data.local.di.SharedPrefsModule
import com.github.jaskelai.object_tracking.data.network.di.RetrofitYandexTranslateModule
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.github.jaskelai.object_tracking.presentation.di.MainSubcomponent
import dagger.BindsInstance
import dagger.Component

@PerApp
@Component(
    modules = [
        RepositoryModule::class,
        AuthModule::class,
        SharedPrefsModule::class,
        RoomDBModule::class,
        NetModule::class,
        RetrofitYandexTranslateModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance appContext: Context): AppComponent
    }

    fun inject(appDelegate: AppDelegate)

    fun mainSubcomponentBuilder(): MainSubcomponent.Builder
}
