package com.github.jaskelai.object_tracking.presentation.ui.main_flow.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.MainFlowFragment
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.all_items.di.AllItemsSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.item_details.di.ItemDetailsSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.profile.di.ProfileSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.main.di.UserItemsSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.di.NewItemSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser.di.PhotoChooserSubcomponent
import dagger.Subcomponent

@Subcomponent(modules = [MainFlowModule::class])
@PerFragment
interface MainFlowSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainFlowSubcomponent
    }

    fun inject(mainFlowFragment: MainFlowFragment)

    fun allItemsSubcomponent(): AllItemsSubcomponent.Builder
    fun profileSubcomponent(): ProfileSubcomponent.Builder
    fun userItemsSubcomponent(): UserItemsSubcomponent.Builder
    fun newItemSubcomponent(): NewItemSubcomponent.Builder
    fun photoChooserSubcomponent(): PhotoChooserSubcomponent.Builder
    fun itemDetailsSubcomponent(): ItemDetailsSubcomponent.Builder
}