package com.bael.dads.domain.home.di.module.mapper

import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.domain.home.mapper.DadJokeDBMapper
import com.bael.dads.domain.home.mapper.DadJokeRemoteMapper
import com.bael.dads.domain.home.mapper.RemoteMetaMapper
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.lib.database.entity.RemoteMeta
import com.bael.dads.lib.remote.response.DadJokesResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB
import com.bael.dads.lib.remote.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface MapperModule {

    @Binds
    @Singleton
    fun bindDadJokeRemoteMapper(mapper: DadJokeRemoteMapper): Mapper<DadJokeRemote, DadJokeDB>

    @Binds
    @Singleton
    fun bindDadJokeDBMapper(mapper: DadJokeDBMapper): Mapper<DadJokeDB, DadJoke>

    @Binds
    @Singleton
    fun bindRemoteMetaMapper(mapper: RemoteMetaMapper): Mapper<DadJokesResponse, RemoteMeta>
}
