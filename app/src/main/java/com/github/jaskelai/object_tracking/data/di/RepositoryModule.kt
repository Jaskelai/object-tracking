package com.github.jaskelai.object_tracking.data.di

import com.github.jaskelai.object_tracking.data.repository.photo.PhotoRepositoryImpl
import com.github.jaskelai.object_tracking.data.repository.user.AuthRepositoryImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.interfaces.PhotoRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    @PerApp
    fun bind1(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @PerApp
    fun bind2(impl: PhotoRepositoryImpl): PhotoRepository
}
