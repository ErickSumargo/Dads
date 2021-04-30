package com.bael.dads.data.database.repository

import com.bael.dads.data.database.entity.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface DadJokeRepository {

    suspend fun insertDadJokes(dadJokes: List<DadJoke>)

    suspend fun loadDadJokeFeed(id: Int, limit: Int): List<DadJoke>

    suspend fun loadSeenDadJoke(term: String, cursor: Int, limit: Int): List<DadJoke>

    suspend fun loadFavoredDadJoke(term: String, cursor: Long, limit: Int): List<DadJoke>

    suspend fun loadDadJoke(id: Int): DadJoke?

    suspend fun loadLatestDadJoke(): DadJoke?

    suspend fun observeDadJoke(id: Int): Flow<DadJoke>

    suspend fun setDadJokeSeen(id: Int): Int

    suspend fun favorDadJoke(id: Int, favored: Boolean): Int

    suspend fun deleteAllDadJokes(): Int
}
