package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.new_item.NewItemViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface NewItemModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewItemViewModel::class)
    fun providNewItemViewModel(newItemViewModel: NewItemViewModel): ViewModel
}