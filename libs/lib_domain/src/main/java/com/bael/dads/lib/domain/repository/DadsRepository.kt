package com.bael.dads.lib.domain.repository

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.domain.model.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsRepository {

    fun loadDadJokeFeed(cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>>

    fun loadFavoredDadJoke(
        term: String,
        cursor: DadJoke?,
        limit: Int
    ): Flow<Response<List<DadJoke>>>

    fun loadSeenDadJoke(term: String, cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>>

    fun loadDadJoke(id: Int): Flow<Response<DadJoke>>

    fun observeDadJoke(dadJoke: DadJoke): Flow<Response<DadJoke>>

    fun setDadJokeSeen(dadJoke: DadJoke): Flow<Boolean>

    fun favorDadJoke(dadJoke: DadJoke, favored: Boolean): Flow<Boolean>
}
