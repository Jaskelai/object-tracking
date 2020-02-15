package com.github.jaskelai.object_tracking.presentation.ui.auth.sign_in_phone.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.auth.sign_in_phone.SignInPhoneViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SignInPhoneModule {

    @Binds
    @IntoMap
    @ViewModelKey(SignInPhoneViewModel::class)
    fun provideSignInViewModel(signInPhoneViewModel: SignInPhoneViewModel): ViewModel
}
