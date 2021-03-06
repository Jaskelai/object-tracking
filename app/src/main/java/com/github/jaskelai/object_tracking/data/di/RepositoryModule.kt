package com.github.jaskelai.object_tracking.data.di

import com.github.jaskelai.object_tracking.data.repository.item.ItemRepositoryImpl
import com.github.jaskelai.object_tracking.data.repository.photo.PhotoRepositoryImpl
import com.github.jaskelai.object_tracking.data.repository.translation.TranslationRepositoryImpl
import com.github.jaskelai.object_tracking.data.repository.user.AuthRepositoryImpl
import com.github.jaskelai.object_tracking.di.scope.PerApp
import com.github.jaskelai.object_tracking.domain.interfaces.AuthRepository
import com.github.jaskelai.object_tracking.domain.interfaces.ItemRepository
import com.github.jaskelai.object_tracking.domain.interfaces.PhotoRepository
import com.github.jaskelai.object_tracking.domain.interfaces.TranslationRepository
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

    @Binds
    @PerApp
    fun bind3(impl: TranslationRepositoryImpl): TranslationRepository

    @Binds
    @PerApp
    fun bind4(impl: ItemRepositoryImpl): ItemRepository
}
