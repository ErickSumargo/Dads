package com.bael.dads.lib.database.repository

import com.bael.dads.lib.database.entity.RemoteMeta

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface RemoteMetaRepository {

    suspend fun loadRemoteMeta(): RemoteMeta?

    suspend fun insertRemoteMeta(meta: RemoteMeta)
}
