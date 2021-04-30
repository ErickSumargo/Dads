package com.bael.dads.domain.home.di.module.mapper

import com.bael.dads.data.database.entity.RemoteMeta
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.domain.home.mapper.DadJokeDBMapper
import com.bael.dads.domain.home.mapper.DadJokeRemoteMapper
import com.bael.dads.domain.home.mapper.RemoteMetaMapper
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.shared.mapper.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB
import com.bael.dads.data.remote.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    @Provides
    @Singleton
    fun provideDadJokeRemoteMapper(): Mapper<DadJokeRemote, DadJokeDB> {
        return DadJokeRemoteMapper()
    }

    @Provides
    @Singleton
    fun provideDadJokeDBMapper(): Mapper<DadJokeDB, DadJoke> {
        return DadJokeDBMapper()
    }

    @Provides
    @Singleton
    fun provideRemoteMetaMapper(): Mapper<DadJokesResponse, RemoteMeta> {
        return RemoteMetaMapper()
    }
}
