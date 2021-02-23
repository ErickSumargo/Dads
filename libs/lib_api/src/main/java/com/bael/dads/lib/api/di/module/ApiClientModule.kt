package com.bael.dads.lib.api.di.module

import com.apollographql.apollo.ApolloClient
import com.bael.dads.lib.api.di.qualifier.BaseUrlQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object ApiClientModule {

    @Provides
    @Singleton
    @BaseUrlQualifier
    fun provideBaseUrl(): String {
        return "https://dads-engine.herokuapp.com/"
    }

    @Provides
    @Singleton
    fun provideApollo(
        @BaseUrlQualifier url: String,
        okHttpClient: OkHttpClient,
    ): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(url)
            .okHttpClient(okHttpClient)
            .build()
    }
}
