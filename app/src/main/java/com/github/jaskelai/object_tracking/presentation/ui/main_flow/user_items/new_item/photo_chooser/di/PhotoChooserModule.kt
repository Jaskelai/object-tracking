package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.photo_chooser.PhotoChooserViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PhotoChooserModule {

    @Binds
    @IntoMap
    @ViewModelKey(PhotoChooserViewModel::class)
    fun providPhotoChooserViewModel(photoChooserViewModel: PhotoChooserViewModel): ViewModel
}