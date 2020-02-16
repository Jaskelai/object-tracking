package com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone.AuthPhoneFragment
import dagger.Subcomponent

@Subcomponent(modules = [AuthPhoneModule::class])
@PerFragment
interface AuthPhoneSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): AuthPhoneSubcomponent
    }

    fun inject(authFragment: AuthPhoneFragment)
}
