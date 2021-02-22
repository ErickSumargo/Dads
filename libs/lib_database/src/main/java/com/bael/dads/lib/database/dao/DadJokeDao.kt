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

    @Insert(onConflict = REPLACE)
    suspend fun insertDadJokes(dadJokes: List<DadJoke>)

    @Query("SELECT * FROM dad_joke WHERE id > :id AND NOT seen LIMIT :limit")
    suspend fun loadDadJokeFeed(id: Int, limit: Int): List<DadJoke>

    @Query("SELECT * FROM dad_joke WHERE id < :cursor AND setup LIKE '%' || :term || '%' AND seen ORDER BY id DESC LIMIT :limit")
    suspend fun loadSeenDadJoke(term: String, cursor: Int, limit: Int): List<DadJoke>

    @Query("SELECT * FROM dad_joke WHERE updated_at < :updatedAt AND setup LIKE '%' || :term || '%' AND favored ORDER BY updated_at DESC LIMIT :limit")
    suspend fun loadFavoredDadJoke(term: String, updatedAt: Long, limit: Int): List<DadJoke>

    @Query("SELECT * FROM dad_joke WHERE id = :id")
    suspend fun loadDadJoke(id: Int): DadJoke?

    @Query("SELECT * FROM dad_joke ORDER BY id DESC limit 1")
    suspend fun loadLatestDadJoke(): DadJoke?

    @Query("SELECT * FROM dad_joke WHERE id = :id")
    fun observeDadJoke(id: Int): Flow<DadJoke>

    @Query("UPDATE dad_joke SET seen = 1, updated_at = :updatedAt WHERE id = :id")
    suspend fun setDadJokeSeen(id: Int, updatedAt: Long): Int

    @Query("UPDATE dad_joke SET favored = :favored, updated_at = :updatedAt WHERE id = :id")
    suspend fun favorDadJoke(id: Int, favored: Boolean, updatedAt: Long): Int
}
