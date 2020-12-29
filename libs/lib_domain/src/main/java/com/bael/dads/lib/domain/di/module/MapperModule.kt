package com.bael.dads.lib.domain.di.module

import com.bael.dads.lib.api.model.DadJoke
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.data.mapper.Mapper
import com.bael.dads.lib.database.entity.RemoteMeta
import com.bael.dads.lib.domain.mapper.data.DadJokeDBMapper
import com.bael.dads.lib.domain.mapper.data.DadJokeRemoteMapper
import com.bael.dads.lib.domain.mapper.data.RemoteMetaMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import com.bael.dads.lib.api.model.DadJoke as DadJokeRemote
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class MapperModule {

    @Binds
    @Singleton
    internal abstract fun bindDadJokeRemoteMapper(
        mapper: DadJokeRemoteMapper
    ): Mapper<DadJokeRemote, DadJokeDB>

    @Binds
    @Singleton
    internal abstract fun bindDadJokeDBMapper(
        mapper: DadJokeDBMapper
    ): Mapper<DadJokeDB, DadJoke>

    @Binds
    @Singleton
    internal abstract fun bindRemoteMetaMapper(
        mapper: RemoteMetaMapper
    ): Mapper<DadJokesResponse, RemoteMeta>
}
