package com.bael.lib.api.di.module

import com.bael.lib.api.interceptor.AuthInterceptor
import com.bael.lib.api.interceptor.ContentTypeInterceptor
import com.bael.lib.api.interceptor.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
internal object OkHttpClientModule {

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = BODY }
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
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
