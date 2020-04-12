package com.github.jaskelai.object_tracking.presentation.ui.main_flow.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.main_flow.MainFlowViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainFlowModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainFlowViewModel::class)
    fun provideMainFlowViewModel(mainFlowViewModel: MainFlowViewModel): ViewModel
}