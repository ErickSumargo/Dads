package com.bael.dads.lib.api.di.module

import com.apollographql.apollo.ApolloClient
import com.bael.dads.lib.api.di.qualifier.BaseUrlQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
internal object ApiClientModule {

    @Provides
    @Singleton
    @BaseUrlQualifier
    internal fun provideBaseUrl(): String = "http://10.0.2.2:4000"

    @Provides
    @Singleton
    internal fun provideApollo(
        @BaseUrlQualifier url: String,
        okHttpClient: OkHttpClient,
    ): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(url)
            .okHttpClient(okHttpClient)
            .build()
    }
}
