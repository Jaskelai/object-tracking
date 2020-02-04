package com.github.jaskelai.object_tracking.presentation.di

import com.github.jaskelai.object_tracking.di.scope.PerActivity
import com.github.jaskelai.object_tracking.presentation.MainActivity
import com.github.jaskelai.object_tracking.presentation.ui.sign_in.di.SignInSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.start_screen.di.StartScreenSubcomponent
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@PerActivity
interface MainSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainSubcomponent
    }

    fun inject(mainActivity: MainActivity)

    fun startScreenSubcomponentBuilder(): StartScreenSubcomponent.Builder
    fun signInSubcomponentBuilder(): SignInSubcomponent.Builder
}
