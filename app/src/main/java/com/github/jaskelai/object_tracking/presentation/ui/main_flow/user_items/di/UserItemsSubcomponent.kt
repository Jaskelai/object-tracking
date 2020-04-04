package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.di

import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.PerMainFlow
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.UserItemsFragment
import dagger.Subcomponent

@Subcomponent(modules = [UserItemsModule::class])
@PerMainFlow
interface UserItemsSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): UserItemsSubcomponent
    }

    fun inject(userItemsFragment: UserItemsFragment)
}