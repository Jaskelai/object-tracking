package com.github.jaskelai.object_tracking.presentation.ui.sign_in.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.sign_in.SignInViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignInModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun provideSignInViewModel(signInViewModel: SignInViewModel): ViewModel
}
