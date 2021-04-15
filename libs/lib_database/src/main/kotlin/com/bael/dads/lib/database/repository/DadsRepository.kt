package com.bael.dads.lib.database.repository

import com.bael.dads.lib.database.entity.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface DadsRepository {

    suspend fun insertDadJokes(dadJokes: List<DadJoke>)

    suspend fun loadDadJokeFeed(id: Int, limit: Int): List<DadJoke>

    suspend fun loadSeenDadJoke(term: String, cursor: Int, limit: Int): List<DadJoke>

    suspend fun loadFavoredDadJoke(term: String, updatedAt: Long, limit: Int): List<DadJoke>

    suspend fun loadDadJoke(id: Int): DadJoke?

    suspend fun loadLatestDadJoke(): DadJoke?

    fun observeDadJoke(id: Int): Flow<DadJoke>

    suspend fun setDadJokeSeen(id: Int, updatedAt: Long): Int

    suspend fun favorDadJoke(id: Int, favored: Boolean, updatedAt: Long): Int
}
