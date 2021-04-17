package com.bael.dads.lib.database.test

import android.content.Context
import androidx.room.Room.inMemoryDatabaseBuilder
import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.database.DadsRoomDatabase
import com.bael.dads.lib.database.dao.DadJokeDao
import com.bael.dads.lib.database.dao.RemoteMetaDao
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class FakeDadsDatabase @Inject constructor(
    @ApplicationContext context: Context
) : DadsDatabase {
    private val database: DadsRoomDatabase by lazy {
        inMemoryDatabaseBuilder(context, DadsRoomDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    override val dadJoke: DadJokeDao
        get() = database.dadJoke

    override val remoteMeta: RemoteMetaDao
        get() = database.remoteMeta

    override fun closeConnection() {
        database.close()
    }
}
