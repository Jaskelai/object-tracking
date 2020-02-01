package com.github.jaskelai.object_tracking.presentation.di

import com.github.jaskelai.object_tracking.di.scope.PerActivity
import com.github.jaskelai.object_tracking.presentation.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@PerActivity
interface MainSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainSubcomponent
    }

    fun inject(mainActivity: MainActivity)
}