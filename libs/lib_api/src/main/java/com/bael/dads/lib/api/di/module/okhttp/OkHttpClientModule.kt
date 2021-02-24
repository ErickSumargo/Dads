package com.bael.dads.lib.api.di.module.okhttp

import com.bael.dads.lib.api.BuildConfig.DEBUG
import com.bael.dads.lib.api.interceptor.AuthInterceptor
import com.bael.dads.lib.api.interceptor.ContentTypeInterceptor
import com.bael.dads.lib.api.interceptor.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object OkHttpClientModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = BODY.takeIf { DEBUG } ?: NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkInterceptor: NetworkInterceptor,
        authInterceptor: AuthInterceptor,
        contentTypeInterceptor: ContentTypeInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkInterceptor)
            .addInterceptor(authInterceptor)
            .addInterceptor(contentTypeInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }
}
