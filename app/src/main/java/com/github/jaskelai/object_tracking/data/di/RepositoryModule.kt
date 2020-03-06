package com.github.jaskelai.object_tracking.data.di

import com.github.jaskelai.object_tracking.data.repository.AuthRepositoryImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @PerApp
    fun bind1(impl: AuthRepositoryImpl): AuthRepository
}
