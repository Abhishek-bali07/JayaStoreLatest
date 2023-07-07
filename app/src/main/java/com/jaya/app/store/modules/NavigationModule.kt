package com.jaya.app.store.modules

import com.jaya.app.store.core.utils.helper.AppNavigator
import com.jaya.app.store.navigation.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Singleton
    @Binds
    fun bindNavigator(appNavigatorImpl: AppNavigatorImpl): AppNavigator
}