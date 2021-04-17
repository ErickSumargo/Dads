package com.bael.dads.domain.home.di.module.usecase

import com.bael.dads.domain.home.interactor.FavorDadJokeInteractor
import com.bael.dads.domain.home.interactor.LoadDadJokeFeedInteractor
import com.bael.dads.domain.home.interactor.LoadDadJokeInteractor
import com.bael.dads.domain.home.interactor.LoadFavoredDadJokeInteractor
import com.bael.dads.domain.home.interactor.LoadSeenDadJokeInteractor
import com.bael.dads.domain.home.interactor.ObserveDadJokeInteractor
import com.bael.dads.domain.home.interactor.SetDadJokeSeenInteractor
import com.bael.dads.domain.home.usecase.FavorDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadFavoredDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadSeenDadJokeUseCase
import com.bael.dads.domain.home.usecase.ObserveDadJokeUseCase
import com.bael.dads.domain.home.usecase.SetDadJokeSeenUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface UseCaseModule {

    @Binds
    @Singleton
    fun bindFavorDadJokeUseCase(
        interactor: FavorDadJokeInteractor
    ): FavorDadJokeUseCase

    @Binds
    @Singleton
    fun bindLoadDadJokeFeedUseCase(
        interactor: LoadDadJokeFeedInteractor
    ): LoadDadJokeFeedUseCase

    @Binds
    @Singleton
    fun bindLoadDadJokeUseCase(
        interactor: LoadDadJokeInteractor
    ): LoadDadJokeUseCase

    @Binds
    @Singleton
    fun bindLoadFavoredDadJokeUseCase(
        interactor: LoadFavoredDadJokeInteractor
    ): LoadFavoredDadJokeUseCase

    @Binds
    @Singleton
    fun bindLoadSeenDadJokeUseCase(
        interactor: LoadSeenDadJokeInteractor
    ): LoadSeenDadJokeUseCase

    @Binds
    @Singleton
    fun bindObserveDadJokeUseCase(
        interactor: ObserveDadJokeInteractor
    ): ObserveDadJokeUseCase

    @Binds
    @Singleton
    fun bindSetDadJokeSeenUseCase(
        interactor: SetDadJokeSeenInteractor
    ): SetDadJokeSeenUseCase
}
