package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms.AuthSmsFragment
import dagger.Subcomponent

@Subcomponent(modules = [AuthSmsModule::class])
@PerFragment
interface AuthSmsSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): AuthSmsSubcomponent
    }

    fun inject(authFragment: AuthSmsFragment)
}