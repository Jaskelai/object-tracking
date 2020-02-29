package com.github.jaskelai.object_tracking.presentation.ui.set_bio.di

import com.github.jaskelai.object_tracking.di.scope.PerFragment
import com.github.jaskelai.object_tracking.presentation.ui.set_bio.SetBioFragment
import dagger.Subcomponent

@Subcomponent(modules = [SetBioModule::class])
@PerFragment
interface SetBioSubcomponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): SetBioSubcomponent
    }

    fun inject(setBioFragment: SetBioFragment)
}