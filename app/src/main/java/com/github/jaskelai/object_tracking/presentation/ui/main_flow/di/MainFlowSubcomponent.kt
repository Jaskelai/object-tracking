package com.github.jaskelai.object_tracking.presentation.ui.main_flow.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.MainFlowFragment
import dagger.Subcomponent

@Subcomponent(modules = [MainFlowModule::class])
@PerFragment
interface MainFlowSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainFlowSubcomponent
    }

    fun inject(mainFlowFragment: MainFlowFragment)
}