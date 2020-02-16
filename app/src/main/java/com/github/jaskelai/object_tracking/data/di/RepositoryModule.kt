package com.github.jaskelai.object_tracking.data.di

import com.github.jaskelai.object_tracking.data.repository.AuthRepositoryImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @PerApp
    @Binds
    fun bindAuthRepository(rep: AuthRepositoryImpl): AuthRepository
}
