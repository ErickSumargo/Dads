package com.bael.dads.lib.database.repository

import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.database.dao.RemoteMetaDao
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultRemoteMetaRepository @Inject constructor(database: DadsDatabase) :
    RemoteMetaRepository,
    RemoteMetaDao by database.remoteMeta
