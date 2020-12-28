package com.bael.dads.lib.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @ColumnInfo(name = "joke")
    val joke: String,
    @ColumnInfo(name = "seen")
    val seen: Boolean = false
)
