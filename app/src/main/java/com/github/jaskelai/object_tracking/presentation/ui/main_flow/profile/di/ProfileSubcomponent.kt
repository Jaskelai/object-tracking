package com.github.jaskelai.object_tracking.presentation.ui.main_flow.profile.di

import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.PerMainFlow
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.profile.ProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [ProfileModule::class])
@PerMainFlow
interface ProfileSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): ProfileSubcomponent
    }

    fun inject(profileFragment: ProfileFragment)
}