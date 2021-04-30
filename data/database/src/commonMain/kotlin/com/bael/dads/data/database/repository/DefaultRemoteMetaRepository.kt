package com.bael.dads.data.database.repository

import com.bael.dads.data.database.DadsDatabase
import com.bael.dads.data.database.entity.RemoteMeta

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultRemoteMetaRepository(database: DadsDatabase) :
    RemoteMetaRepository,
    DadsDatabase by database {

    override suspend fun loadRemoteMeta(): RemoteMeta? {
        return remoteMetaQueries.loadRemoteMeta()
            .executeAsOneOrNull()
    }

    override suspend fun insertRemoteMeta(remoteMeta: RemoteMeta): Int {
        remoteMetaQueries.insertRemoteMeta(cursor = remoteMeta.cursor)
        return 1
    }
}
