package com.bael.dads.domain.home.di.module.mapper

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
 * Created by ErickSumargo on 01/05/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object ListMapperModule {

    @Provides
    @Singleton
    fun provideDadJokesRemoteMapper(
        mapper: Mapper<DadJokeRemote, DadJokeDB>
    ): ListMapper<DadJokeRemote, DadJokeDB> {
        return ListMapper(mapper)
    }
}
