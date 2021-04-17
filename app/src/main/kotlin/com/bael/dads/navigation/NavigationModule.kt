package com.bael.dads.navigation

import com.bael.dads.lib.navigation.HomeNavigation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindHomeNavigation(homeNavigation: HomeNavigationImpl): HomeNavigation
}
