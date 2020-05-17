package com.github.jaskelai.object_tracking.presentation.ui.main_flow.item_details.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.item_details.ItemDetailsViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ItemDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ItemDetailsViewModel::class)
    fun provideItemDetailsViewModel(itemDetailsViewModel: ItemDetailsViewModel): ViewModel
}