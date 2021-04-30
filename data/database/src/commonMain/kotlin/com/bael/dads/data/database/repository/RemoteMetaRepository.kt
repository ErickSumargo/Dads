package com.bael.dads.data.database.repository

import com.bael.dads.data.database.entity.RemoteMeta

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface RemoteMetaRepository {

    suspend fun loadRemoteMeta(): RemoteMeta?

    suspend fun insertRemoteMeta(remoteMeta: RemoteMeta): Int
}
