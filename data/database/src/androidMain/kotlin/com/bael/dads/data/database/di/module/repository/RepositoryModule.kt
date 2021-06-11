package com.bael.dads.data.database.di.module.repository

import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.data.database.repository.DefaultDadJokeRepository
import com.bael.dads.data.database.repository.DefaultRemoteMetaRepository
import com.bael.dads.data.database.repository.RemoteMetaRepository
import com.google.firebase.database.DatabaseReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/04/21.
 */

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {

    @Provides
    @Singleton
    fun provideDadJokeRepository(database: DatabaseReference): DadJokeRepository {
        return DefaultDadJokeRepository(database)
    }

    @Provides
    @Singleton
    fun provideRemoteMetaRepository(database: DatabaseReference): RemoteMetaRepository {
        return DefaultRemoteMetaRepository(database)
    }
}
