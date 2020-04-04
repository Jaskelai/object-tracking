package com.github.jaskelai.object_tracking.presentation.ui.main_flow.all_items.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.all_items.AllItemsViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AllItemsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AllItemsViewModel::class)
    fun provideAllItemsViewModel(allItemsViewModel: AllItemsViewModel): ViewModel
}