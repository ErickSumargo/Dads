package com.bael.dads.domain.home.di.module.usecase

import com.bael.dads.data.database.entity.RemoteMeta
import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.data.database.repository.RemoteMetaRepository
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.data.remote.service.DadsService
import com.bael.dads.domain.home.interactor.FavorDadJokeInteractor
import com.bael.dads.domain.home.interactor.LoadDadJokeFeedInteractor
import com.bael.dads.domain.home.interactor.LoadDadJokeInteractor
import com.bael.dads.domain.home.interactor.LoadFavoredDadJokeInteractor
import com.bael.dads.domain.home.interactor.LoadSeenDadJokeInteractor
import com.bael.dads.domain.home.interactor.ObserveDadJokeInteractor
import com.bael.dads.domain.home.interactor.SetDadJokeSeenInteractor
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.FavorDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.domain.home.usecase.LoadDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadFavoredDadJokeUseCase
import com.bael.dads.domain.home.usecase.LoadSeenDadJokeUseCase
import com.bael.dads.domain.home.usecase.ObserveDadJokeUseCase
import com.bael.dads.domain.home.usecase.SetDadJokeSeenUseCase
import com.bael.dads.shared.mapper.ListMapper
import com.bael.dads.shared.mapper.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB
import com.bael.dads.data.remote.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @Provides
    @Singleton
    fun provideFavorDadJokeUseCase(repository: DadJokeRepository): FavorDadJokeUseCase {
        return FavorDadJokeInteractor(repository)
    }

    @Provides
    @Singleton
    fun provideLoadDadJokeFeedUseCase(
        dadJokeRepository: DadJokeRepository,
        remoteMetaRepository: RemoteMetaRepository,
        service: DadsService,
        dadJokeDBMapper: Mapper<DadJokeDB, DadJoke>,
        dadJokesRemoteMapper: ListMapper<DadJokeRemote, DadJokeDB>,
        remoteMetaMapper: Mapper<DadJokesResponse, RemoteMeta>
    ): LoadDadJokeFeedUseCase {
        return LoadDadJokeFeedInteractor(
            dadJokeRepository,
            remoteMetaRepository,
            service,
            dadJokeDBMapper,
            dadJokesRemoteMapper,
            remoteMetaMapper
        )
    }

    @Provides
    @Singleton
    fun provideLoadDadJokeUseCase(
        repository: DadJokeRepository,
        mapper: Mapper<DadJokeDB, DadJoke>
    ): LoadDadJokeUseCase {
        return LoadDadJokeInteractor(repository, mapper)
    }

    @Provides
    @Singleton
    fun provideLoadFavoredDadJokeUseCase(
        repository: DadJokeRepository,
        mapper: Mapper<DadJokeDB, DadJoke>
    ): LoadFavoredDadJokeUseCase {
        return LoadFavoredDadJokeInteractor(repository, mapper)
    }

    @Provides
    @Singleton
    fun provideLoadSeenDadJokeUseCase(
        repository: DadJokeRepository,
        mapper: Mapper<DadJokeDB, DadJoke>
    ): LoadSeenDadJokeUseCase {
        return LoadSeenDadJokeInteractor(repository, mapper)
    }

    @Provides
    @Singleton
    fun provideObserveDadJokeUseCase(
        repository: DadJokeRepository,
        mapper: Mapper<DadJokeDB, DadJoke>
    ): ObserveDadJokeUseCase {
        return ObserveDadJokeInteractor(repository, mapper)
    }

    @Provides
    @Singleton
    fun provideSetDadJokeSeenUseCase(repository: DadJokeRepository): SetDadJokeSeenUseCase {
        return SetDadJokeSeenInteractor(repository)
    }
}
