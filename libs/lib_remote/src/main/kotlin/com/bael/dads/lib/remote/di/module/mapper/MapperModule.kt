package com.bael.dads.lib.remote.di.module.mapper

import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.lib.remote.mapper.data.DadJokeClientMapper
import com.bael.dads.lib.remote.mapper.data.DadJokesResponseClientMapper
import com.bael.dads.lib.remote.model.DadJoke
import com.bael.dads.lib.remote.response.DadJokesResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.lib.remote.query.DadJokesQuery.Data as DadJokesQueryData
import com.bael.dads.lib.remote.query.DadJokesQuery.Joke as DadJokeClient

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal interface MapperModule {

    @Binds
    @Singleton
    fun bindDadJokeClientMapper(mapper: DadJokeClientMapper): Mapper<DadJokeClient, DadJoke>

    @Binds
    @Singleton
    fun bindDadJokesResponseClientMapper(
        mapper: DadJokesResponseClientMapper
    ): Mapper<DadJokesQueryData, DadJokesResponse>
}
