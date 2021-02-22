package com.bael.dads.lib.domain.di.module.interactor

import com.bael.dads.lib.domain.interactor.home.DefaultFavorDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.DefaultLoadDadJokeFeedInteractor
import com.bael.dads.lib.domain.interactor.home.DefaultLoadDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.DefaultLoadFavoredDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.DefaultLoadSeenDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.DefaultObserveDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.DefaultSetDadJokeSeenInteractor
import com.bael.dads.lib.domain.interactor.home.FavorDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.LoadDadJokeFeedInteractor
import com.bael.dads.lib.domain.interactor.home.LoadDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.LoadFavoredDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.LoadSeenDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.ObserveDadJokeInteractor
import com.bael.dads.lib.domain.interactor.home.SetDadJokeSeenInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface InteractorModule {

    @Binds
    @Singleton
    fun bindLoadDadJokeFeedInteractor(
        interactor: DefaultLoadDadJokeFeedInteractor
    ): LoadDadJokeFeedInteractor

    @Binds
    @Singleton
    fun bindLoadSeenDadJokeInteractor(
        interactor: DefaultLoadSeenDadJokeInteractor
    ): LoadSeenDadJokeInteractor

    @Binds
    @Singleton
    fun bindLoadFavoredDadJokeInteractor(
        interactor: DefaultLoadFavoredDadJokeInteractor
    ): LoadFavoredDadJokeInteractor

    @Binds
    @Singleton
    fun bindLoadDadJokeInteractor(
        interactor: DefaultLoadDadJokeInteractor
    ): LoadDadJokeInteractor

    @Binds
    @Singleton
    fun bindObserveDadJokeInteractor(
        interactor: DefaultObserveDadJokeInteractor
    ): ObserveDadJokeInteractor

    @Binds
    @Singleton
    fun bindSetDadJokeSeenInteractor(
        interactor: DefaultSetDadJokeSeenInteractor
    ): SetDadJokeSeenInteractor

    @Binds
    @Singleton
    fun bindFavorDadJokeInteractor(
        interactor: DefaultFavorDadJokeInteractor
    ): FavorDadJokeInteractor
}
