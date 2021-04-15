package com.bael.dads.lib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Entity(tableName = "remote_meta")
data class RemoteMeta(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 1,
    @ColumnInfo(name = "cursor")
    val cursor: String?
)
