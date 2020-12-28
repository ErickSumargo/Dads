package com.bael.lib.api.di.module

import com.apollographql.apollo.ApolloClient
import com.bael.lib.api.di.qualifier.BaseUrlQualifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    internal fun provideBaseUrl(): String = ""

    @Provides
    @Singleton
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        @BaseUrlQualifier url: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

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
