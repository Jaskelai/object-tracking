package com.github.jaskelai.object_tracking.presentation.ui.set_bio.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.set_bio.SetBioViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SetBioModule {

    @Binds
    @IntoMap
    @ViewModelKey(SetBioViewModel::class)
    fun provideSetBioViewModel(setBioViewModel: SetBioViewModel): ViewModel
}
