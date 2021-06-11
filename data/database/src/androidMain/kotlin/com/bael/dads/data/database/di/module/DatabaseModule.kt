package com.bael.dads.data.database.di.module

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDadsDatabase(): DatabaseReference {
        val database = Firebase.database(url = "https://dads-dc254-default-rtdb.firebaseio.com")
        database.setPersistenceEnabled(true)
        database.reference.keepSynced(true)

        return database.reference
    }
}
