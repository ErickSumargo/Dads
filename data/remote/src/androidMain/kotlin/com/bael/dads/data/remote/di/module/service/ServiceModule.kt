package com.bael.dads.data.remote.di.module.service

import com.apollographql.apollo.ApolloClient
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.data.remote.service.DadsApolloService
import com.bael.dads.data.remote.service.DadsService
import com.bael.dads.shared.mapper.ResultMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.bael.dads.data.remote.query.DadJokesQuery.Data as DadJokesQueryData

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideDadsService(
        client: ApolloClient,
        mapper: ResultMapper<DadJokesQueryData, DadJokesResponse>
    ): DadsService {
        return DadsApolloService(client, mapper)
    }
}