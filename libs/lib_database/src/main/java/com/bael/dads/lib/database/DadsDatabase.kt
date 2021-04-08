package com.bael.dads.lib.database

import com.bael.dads.lib.database.dao.DadJokeDao
import com.bael.dads.lib.database.dao.RemoteMetaDao

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsDatabase {
    val dadJoke: DadJokeDao

    val remoteMeta: RemoteMetaDao

    fun closeConnection()
}
