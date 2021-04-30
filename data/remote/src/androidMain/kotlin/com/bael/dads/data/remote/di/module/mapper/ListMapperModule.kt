package com.bael.dads.data.remote.di.module.mapper

import com.bael.dads.data.remote.model.DadJoke
import com.bael.dads.shared.mapper.ListMapper
import com.bael.dads.shared.mapper.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.data.remote.query.DadJokesQuery.Joke as DadJokeQL

/**
 * Created by ErickSumargo on 01/05/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object ListMapperModule {

    @Provides
    @Singleton
    fun provideDadJokeListMapper(
        mapper: Mapper<DadJokeQL, DadJoke>
    ): ListMapper<DadJokeQL, DadJoke> {
        return ListMapper(mapper)
    }
}
