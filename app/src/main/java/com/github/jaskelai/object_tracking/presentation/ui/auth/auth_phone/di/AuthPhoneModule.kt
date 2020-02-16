package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone.AuthPhoneViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthPhoneModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthPhoneViewModel::class)
    fun provideSignInViewModel(authPhoneViewModel: AuthPhoneViewModel): ViewModel
}
