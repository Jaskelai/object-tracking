package com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.user_items.UserItemsViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UserItemsModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserItemsViewModel::class)
    fun provideProfileViewModel(userItemsViewModel: UserItemsViewModel): ViewModel
}