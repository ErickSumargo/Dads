package com.bael.dads.lib.api.di.module

import com.bael.dads.lib.api.mapper.data.DadJokeClientMapper
import com.bael.dads.lib.api.mapper.data.DadJokesResponseClientMapper
import com.bael.dads.lib.api.model.DadJoke
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.data.mapper.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import com.bael.dads.lib.api.query.DadJokesQuery.Data as DadJokesQueryData
import com.bael.dads.lib.api.query.DadJokesQuery.Joke as DadJokeClient

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class MapperModule {

    @Binds
    @Singleton
    internal abstract fun bindDadJokeClientMapper(
        mapper: DadJokeClientMapper
    ): Mapper<DadJokeClient, DadJoke>

    @Binds
    @Singleton
    internal abstract fun bindDadJokesResponseClientMapper(
        mapper: DadJokesResponseClientMapper
    ): Mapper<DadJokesQueryData, DadJokesResponse>
}
