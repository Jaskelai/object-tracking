package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.di

import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.PerMainFlow
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.NewItemFragment
import dagger.Subcomponent

@Subcomponent(modules = [NewItemModule::class])
@PerMainFlow
interface NewItemSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): NewItemSubcomponent
    }

    fun inject(newItemFragment: NewItemFragment)
}