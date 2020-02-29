package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms.di

import androidx.lifecycle.ViewModel
import com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms.AuthSmsViewModel
import com.github.jaskelai.object_tracking.presentation.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthSmsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthSmsViewModel::class)
    fun provideSmsViewModel(authSmsViewModel: AuthSmsViewModel): ViewModel
}
