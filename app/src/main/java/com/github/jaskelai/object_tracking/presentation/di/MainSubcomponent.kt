package com.github.jaskelai.object_tracking.presentation.di

import com.github.jaskelai.object_tracking.di.scope.PerActivity
import com.github.jaskelai.object_tracking.presentation.MainActivity
import com.github.jaskelai.object_tracking.presentation.ui.auth.auth_phone.di.AuthPhoneSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.auth.auth_sms.di.AuthSmsSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.auth.set_bio.di.SetBioSubcomponent
import com.github.jaskelai.object_tracking.presentation.ui.auth.start_screen.di.StartScreenSubcomponent
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
@PerActivity
interface MainSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): MainSubcomponent
    }

    fun inject(mainActivity: MainActivity)

    fun startScreenSubcomponentBuilder(): StartScreenSubcomponent.Builder
    fun authPhoneSubcomponentBuilder(): AuthPhoneSubcomponent.Builder
    fun authSmsSubcomponentBuilder(): AuthSmsSubcomponent.Builder
    fun setBioSubcomponentBuilder(): SetBioSubcomponent.Builder
}
