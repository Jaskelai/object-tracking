package com.github.jaskelai.object_tracking.data.di

import com.github.jaskelai.object_tracking.data.repository.PhoneAuthRepositoryImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.github.jaskelai.object_tracking.domain.interfaces.PhoneAuthRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @PerApp
    fun bind1(impl: PhoneAuthRepositoryImpl): PhoneAuthRepository
}
