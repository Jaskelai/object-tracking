package com.github.jaskelai.object_tracking.presentation.ui.start_screen.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.start_screen.StartScreenFragment
import dagger.Subcomponent

@Subcomponent(modules = [StartScreenModule::class])
@PerFragment
interface StartScreenSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): StartScreenSubcomponent
    }

    fun inject(startScreenFragment: StartScreenFragment)
}
