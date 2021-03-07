package com.bael.dads.lib.database.test

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.NONE
import androidx.room.Room.inMemoryDatabaseBuilder
import com.bael.dads.lib.database.DadsRoomDatabase
import com.bael.dads.lib.database.dao.DadJokeDao
import com.bael.dads.lib.database.dao.RemoteMetaDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@VisibleForTesting(otherwise = NONE)
internal class DadsFakeDatabase @Inject constructor(
    @ApplicationContext context: Context
) : DadsTestableDatabase {
    private val database: DadsRoomDatabase by lazy {
        inMemoryDatabaseBuilder(context, DadsRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    override val dadJoke: DadJokeDao
        get() = database.dadJoke

    override val remoteMeta: RemoteMetaDao
        get() = database.remoteMeta

    override fun close() {
        database.close()
    }
}
