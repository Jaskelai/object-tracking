package com.github.jaskelai.object_tracking.presentation.ui.main_flow.item_details.di

import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.PerMainFlow
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.item_details.ItemDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [ItemDetailsModule::class])
@PerMainFlow
interface ItemDetailsSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): ItemDetailsSubcomponent
    }

    fun inject(itemDetailsFragment: ItemDetailsFragment)
}