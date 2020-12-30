package com.bael.dads.lib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.bael.dads.lib.database.entity.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Dao
interface DadJokeDao {

    @Query("SELECT * FROM dad_joke WHERE NOT seen LIMIT :limit")
    fun loadHighlights(limit: Int): Flow<List<DadJoke>>

    @Query("SELECT * FROM dad_joke WHERE id > :cursor AND setup LIKE '%' || :term || '%' AND seen ORDER BY id DESC LIMIT :limit")
    fun loadHistories(term: String, cursor: Int, limit: Int): Flow<List<DadJoke>>

    @Insert(onConflict = REPLACE)
    suspend fun insertDadJokes(dadJokes: List<DadJoke>)

    @Query("UPDATE dad_joke SET seen = 1 WHERE id = :id")
    suspend fun markDadJokeSeen(id: Int)
}
