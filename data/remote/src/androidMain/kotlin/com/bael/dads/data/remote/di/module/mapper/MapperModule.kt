package com.bael.dads.data.remote.di.module.mapper

import com.bael.dads.data.remote.mapper.DadJokeMapper
import com.bael.dads.data.remote.mapper.DadJokesResponseMapper
import com.bael.dads.data.remote.model.DadJoke
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.shared.mapper.ListMapper
import com.bael.dads.shared.mapper.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.data.remote.query.DadJokesQuery.Data as DadJokesQueryData
import com.bael.dads.data.remote.query.DadJokesQuery.Joke as DadJokeQL

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object MapperModule {

    @Provides
    @Singleton
    fun provideDadJokeMapper(): Mapper<DadJokeQL, DadJoke> {
        return DadJokeMapper()
    }

    @Provides
    @Singleton
    fun provideDadJokesResponseMapper(
        listMapper: ListMapper<DadJokeQL, DadJoke>
    ): Mapper<DadJokesQueryData, DadJokesResponse> {
        return DadJokesResponseMapper(listMapper)
    }
}
