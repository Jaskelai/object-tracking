package com.github.jaskelai.object_tracking.presentation.ui.start_screen.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.start_screen.StartScreenViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface StartScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(StartScreenViewModel::class)
    fun provideStartScreenViewModel(startScreenViewModel: StartScreenViewModel): ViewModel
}
