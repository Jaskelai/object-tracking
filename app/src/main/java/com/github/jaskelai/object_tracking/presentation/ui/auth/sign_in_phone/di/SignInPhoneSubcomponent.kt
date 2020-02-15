package com.github.jaskelai.object_tracking.presentation.ui.auth.sign_in_phone.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.auth.sign_in_phone.SignInPhoneFragment
import dagger.Subcomponent

@Subcomponent(modules = [SignInPhoneModule::class])
@PerFragment
interface SignInPhoneSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignInPhoneSubcomponent
    }

    fun inject(signInFragment: SignInPhoneFragment)
}
