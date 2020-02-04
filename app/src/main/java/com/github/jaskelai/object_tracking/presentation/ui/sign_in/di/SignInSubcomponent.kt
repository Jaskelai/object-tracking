package com.github.jaskelai.object_tracking.presentation.ui.sign_in.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.sign_in.SignInFragment
import dagger.Subcomponent

@Subcomponent(modules = [SignInModule::class])
@PerFragment
interface SignInSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): SignInSubcomponent
    }

    fun inject(signInFragment: SignInFragment)
}
