package com.bael.lib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Entity(tableName = "sample")
data class Sample(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0
)
