package com.bael.dads.lib.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bael.dads.lib.database.entity.DadJoke
import com.bael.dads.lib.database.entity.RemoteMeta

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Database(entities = [DadJoke::class, RemoteMeta::class], version = 1)
abstract class DadsRoomDatabase : RoomDatabase(), DadsDatabase {

    override fun closeConnection() {
        close()
    }
}
