package com.bael.dads.data.remote.di.module.mapper

import com.bael.dads.data.remote.model.DadJoke
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.shared.mapper.ResultMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.data.remote.query.DadJokesQuery.Data as DadJokesQueryData
import com.bael.dads.data.remote.query.DadJokesQuery.Joke as DadJokeQL

/**
 * Created by ErickSumargo on 01/05/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object ResultMapperModule {

    @Provides
    @Singleton
    fun provideDadJokeResultMapper(
        mapper: Mapper<DadJokeQL, DadJoke>
    ): ResultMapper<DadJokeQL, DadJoke> {
        return ResultMapper(mapper)
    }

    @Provides
    @Singleton
    fun provideDadJokeResponseResultMapper(
        mapper: Mapper<DadJokesQueryData, DadJokesResponse>
    ): ResultMapper<DadJokesQueryData, DadJokesResponse> {
        return ResultMapper(mapper)
    }
}
