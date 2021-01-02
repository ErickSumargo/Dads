package com.bael.dads.lib.domain.repository

import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.data.response.Response.Error
import com.bael.dads.lib.data.response.Response.Loading
import com.bael.dads.lib.data.response.Response.Success
import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.domain.mapper.facade.DadsMapper
import com.bael.dads.lib.domain.model.DadJoke
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@ExperimentalCoroutinesApi
internal class DefaultDadsRepository @Inject constructor(
    private val api: DadsService,
    private val database: DadsDatabase,
    private val mapper: DadsMapper
) : DadsRepository {

    override suspend fun loadHighlights(limit: Int): Flow<Response> {
        return database.dadJoke
            .loadHighlights(limit)
            .map { highlights ->
                Success(data = mapper.dadJokesDBMapper.map(highlights))
            }.flatMapLatest { response ->
                dispatchHighlights(response, limit)
            }
    }

    private fun dispatchHighlights(
        response: Success<List<DadJoke>>,
        limit: Int
    ): Flow<Response> {
        return flowOf(response as Response)
            .onCompletion {
                if (response.data.size >= limit) return@onCompletion
                emitAll(fetchDadJokes())
            }
    }

    private fun fetchDadJokes(): Flow<Response> {
        return flow {
            emit(Loading)
            val meta = database.remoteMeta.loadRemoteMeta()

            api.fetchDadJokes(
                cursor = meta?.cursor,
                limit = 20
            ).fold(
                onSuccess = { response ->
                    database.dadJoke.insertDadJokes(
                        dadJokes = mapper.dadJokesRemoteMapper.map(response.dadJokes)
                    )

                    database.remoteMeta.insertRemoteMeta(
                        meta = mapper.remoteMetaMapper.map(response)
                    )
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
