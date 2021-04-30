package com.bael.dads.data.remote.di.module.client

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import com.bael.dads.data.remote.BuildConfig.JWT
import com.bael.dads.data.remote.constant.Server.url
import com.bael.dads.data.remote.interceptor.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/05/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object ApolloClientModule {

    @Provides
    @Singleton
    fun provideApolloClient(networkInterceptor: NetworkInterceptor): ApolloClient {
        return ApolloClient(
            networkTransport = ApolloHttpNetworkTransport(
                serverUrl = url,
                headers = mapOf(
                    "Accept" to "application/json",
                    "Authorization" to "Bearer $JWT"
                )
            ),
            interceptors = listOf(
                networkInterceptor
            )
        )
    }
}
