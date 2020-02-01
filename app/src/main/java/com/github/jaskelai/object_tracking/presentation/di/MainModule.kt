package com.github.jaskelai.object_tracking.presentation.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.MainViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel
}
