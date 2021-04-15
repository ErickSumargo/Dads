package com.bael.dads.lib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bael.dads.lib.database.entity.RemoteMeta

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Dao
interface RemoteMetaDao {

    @Query("SELECT * FROM remote_meta ORDER BY id DESC LIMIT 1")
    suspend fun loadRemoteMeta(): RemoteMeta?

    @Insert(onConflict = REPLACE)
    suspend fun insertRemoteMeta(meta: RemoteMeta)
}
