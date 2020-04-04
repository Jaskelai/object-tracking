package com.github.jaskelai.object_tracking.presentation.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.MainViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProvider
import com.github.jaskelai.object_tracking.presentation.utils.resource_provider.ResourceProviderImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    fun provideResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}
