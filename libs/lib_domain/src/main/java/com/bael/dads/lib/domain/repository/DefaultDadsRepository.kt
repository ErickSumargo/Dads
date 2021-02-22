package com.bael.dads.lib.domain.repository

import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.data.response.Response.Empty
import com.bael.dads.lib.data.response.Response.Error
import com.bael.dads.lib.data.response.Response.Loading
import com.bael.dads.lib.data.response.Response.Success
import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.domain.mapper.facade.DadsMapper
import com.bael.dads.lib.domain.model.DadJoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.lang.System.currentTimeMillis
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultDadsRepository @Inject constructor(
    private val api: DadsService,
    private val database: DadsDatabase,
    private val mapper: DadsMapper
) : DadsRepository {

    override fun loadDadJokeFeed(
        cursor: DadJoke?,
        limit: Int
    ): Flow<Response<List<DadJoke>>> {
        return flow {
            emit(Loading)

            loadDadJokeFeedDB(cursor, limit).let { dadJokes ->
                if (dadJokes.isEmpty()) {
                    emit(Empty)
                } else {
                    emit(Success(data = dadJokes))
                }
            }
        }.flatMapLatest { response ->
            if (response !is Empty) {
                flowOf(response)
            } else {
                fetchDadJokes(cursor, limit)
            }
        }
    }

    override fun loadFavoredDadJoke(
        term: String,
        cursor: DadJoke?,
        limit: Int
    ): Flow<Response<List<DadJoke>>> {
        return flow {
            emit(Loading)

            loadFavoredDadJokeDB(term, cursor, limit).let { dadJokes ->
                if (dadJokes.isEmpty()) {
                    emit(Empty)
                } else {
                    emit(Success(data = dadJokes))
                }
            }
        }
    }

    override fun loadSeenDadJoke(
        term: String,
        cursor: DadJoke?,
        limit: Int
    ): Flow<Response<List<DadJoke>>> {
        return flow {
            emit(Loading)

            loadSeenDadJokeDB(term, cursor, limit).let { dadJokes ->
                if (dadJokes.isEmpty()) {
                    emit(Empty)
                } else {
                    emit(Success(data = dadJokes))
                }
            }
        }
    }

    override fun loadDadJoke(id: Int): Flow<Response<DadJoke>> {
        return flow {
            emit(Loading)

            database.dadJoke.loadDadJoke(id)
                ?.let(mapper.dadJokeDBMapper::map)
                ?.let(::Success)
                ?.also { response ->
                    emit(response)
                }
        }
    }

    override fun observeDadJoke(dadJoke: DadJoke): Flow<Response<DadJoke>> {
        return database.dadJoke
            .observeDadJoke(id = dadJoke.id)
            .filterNotNull()
            .map(mapper.dadJokeDBMapper::map)
            .distinctUntilChanged()
            .map(::Success)
    }

    override fun setDadJokeSeen(dadJoke: DadJoke): Flow<Boolean> {
        return flow {
            val updates = database.dadJoke.setDadJokeSeen(
                id = dadJoke.id,
                updatedAt = currentTimeMillis()
            )
            emit(updates > 0)
        }
    }

    override fun favorDadJoke(dadJoke: DadJoke, favored: Boolean): Flow<Boolean> {
        return flow {
            val updates = database.dadJoke.favorDadJoke(
                id = dadJoke.id,
                favored = favored,
                updatedAt = currentTimeMillis()
            )
            emit(updates > 0)
        }
    }

    private suspend fun loadDadJokeFeedDB(cursor: DadJoke?, limit: Int): List<DadJoke> {
        return database.dadJoke
            .loadDadJokeFeed(id = cursor?.id ?: 0, limit)
            .map(mapper.dadJokeDBMapper::map)
    }

    private suspend fun loadFavoredDadJokeDB(
        term: String,
        cursor: DadJoke?,
        limit: Int
    ): List<DadJoke> {
        return database.dadJoke
            .loadFavoredDadJoke(term, updatedAt = cursor?.updatedAt ?: currentTimeMillis(), limit)
            .map(mapper.dadJokeDBMapper::map)
    }

    private suspend fun loadSeenDadJokeDB(
        term: String,
        cursor: DadJoke?,
        limit: Int
    ): List<DadJoke> {
        return database.dadJoke
            .loadSeenDadJoke(
                term,
                cursor = cursor?.id ?: (database.dadJoke.loadLatestDadJoke()?.id ?: 0) + 1,
                limit
            ).map(mapper.dadJokeDBMapper::map)
    }

    private fun fetchDadJokes(cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>> {
        return flow {
            val meta = database.remoteMeta.loadRemoteMeta()

            api.fetchDadJokes(
                cursor = meta?.cursor,
                limit = 10
            ).fold(
                onSuccess = { response ->
                    database.dadJoke.insertDadJokes(
                        dadJokes = mapper.dadJokesRemoteMapper.map(response.dadJokes)
                    )

                    database.remoteMeta.insertRemoteMeta(
                        meta = mapper.remoteMetaMapper.map(response)
                    )

                    loadDadJokeFeedDB(cursor, limit).let { dadJokes ->
                        if (dadJokes.isEmpty()) {
                            emit(Empty)
                        } else {
                            emit(Success(data = dadJokes))
                        }
                    }
                },
                onFailure = { error ->
                    emit(Error(error = error as Exception))
                }
            )
        }.catch { error ->
            emit(Error(error = error as Exception))
        }
    }
}
