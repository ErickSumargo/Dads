package com.bael.dads.lib.domain.di.module.mapper

import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.data.mapper.Mapper
import com.bael.dads.lib.database.entity.RemoteMeta
import com.bael.dads.lib.domain.mapper.data.DadJokeDBMapper
import com.bael.dads.lib.domain.mapper.data.DadJokeRemoteMapper
import com.bael.dads.lib.domain.mapper.data.RemoteMetaMapper
import com.bael.dads.lib.domain.model.DadJoke
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.lib.api.model.DadJoke as DadJokeRemote
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB

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
