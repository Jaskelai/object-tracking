package com.github.jaskelai.object_tracking.presentation.ui.main_flow.all_items.di

import com.github.jaskelai.object_tracking.presentation.ui.main_flow.all_items.AllItemsFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.PerMainFlow
import dagger.Subcomponent

@Subcomponent(modules = [AllItemsModule::class])
@PerMainFlow
interface AllItemsSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): AllItemsSubcomponent
    }

    fun inject(allItemsFragment: AllItemsFragment)
}