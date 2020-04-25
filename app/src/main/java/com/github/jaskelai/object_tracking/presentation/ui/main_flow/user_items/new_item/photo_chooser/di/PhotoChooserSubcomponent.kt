package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser.di

import com.github.jaskelai.object_tracking.presentation.ui.main_flow.di.PerMainFlow
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser.PhotoChooserBottomSheet
import dagger.Subcomponent

@Subcomponent(modules = [PhotoChooserModule::class])
@PerMainFlow
interface PhotoChooserSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): PhotoChooserSubcomponent
    }

    fun inject(photoChooserBottomSheet: PhotoChooserBottomSheet)
}