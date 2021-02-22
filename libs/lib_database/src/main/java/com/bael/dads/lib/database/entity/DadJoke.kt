package com.bael.dads.lib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Entity(tableName = "dad_joke")
data class DadJoke(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "joke_id")
    val jokeId: String,
    @ColumnInfo(name = "setup")
    val setup: String,
    @ColumnInfo(name = "punchline")
    val punchline: String,
    @ColumnInfo(name = "favored")
    val favored: Boolean,
    @ColumnInfo(name = "seen")
    val seen: Boolean,
    @ColumnInfo(name = "created_at")
    val createdAt: Long,
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long
)
