package com.bael.dads.domain.home.interactor

import com.bael.dads.data.database.entity.RemoteMeta
import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.data.database.repository.RemoteMetaRepository
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.data.remote.service.DadsService
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.shared.mapper.ListMapper
import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.shared.response.Response
import com.bael.dads.shared.response.Response.Empty
import com.bael.dads.shared.response.Response.Error
import com.bael.dads.shared.response.Response.Loading
import com.bael.dads.shared.response.Response.Success
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB
import com.bael.dads.data.remote.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class LoadDadJokeFeedInteractor(
    private val dadJokeRepository: DadJokeRepository,
    private val remoteMetaRepository: RemoteMetaRepository,
    private val service: DadsService,
    private val dadJokeDBMapper: Mapper<DadJokeDB, DadJoke>,
    private val dadJokesRemoteMapper: ListMapper<DadJokeRemote, DadJokeDB>,
    private val remoteMetaMapper: Mapper<DadJokesResponse, RemoteMeta>
) : LoadDadJokeFeedUseCase {

    override fun invoke(cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>> {
        return flow {
            emit(Loading)
            delay(1000)

            // val dadJokes = loadDadJokeFeedDB(cursor, limit)
            val i = cursor?.id ?: 0
            val dadJokes = if (i > 5) {
                listOf()
            } else {
                (i + 1 until i + 4).map {
                    DadJoke(
                        id = it,
                        setup = "Setup $it",
                        punchline = "Punchline $it",
                        favored = false,
                        seen = false,
                        updatedAt = 0
                    )
                }
            }
            if (dadJokes.isEmpty()) {
                emit(Empty)
            } else {
                emit(Success(data = dadJokes))
            }
        }.flatMapLatest { response ->
            if (response !is Empty) {
                flowOf(response)
            } else {
                fetchDadJokes(cursor, limit)
            }
        }
    }

    private suspend fun loadDadJokeFeedDB(cursor: DadJoke?, limit: Int): List<DadJoke> {
        return dadJokeRepository.loadDadJokeFeed(id = cursor?.id ?: 0, limit)
            .map(dadJokeDBMapper::map)
    }

    private fun fetchDadJokes(cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>> {
        return flow {
            val meta = remoteMetaRepository.loadRemoteMeta()

            service.fetchDadJokes(
                cursor = meta?.cursor,
                limit = 20
            ).fold(
                onSuccess = { response ->
                    dadJokeRepository.insertDadJokes(
                        dadJokes = dadJokesRemoteMapper.map(response.dadJokes)
                    )

                    remoteMetaRepository.insertRemoteMeta(
                        remoteMeta = remoteMetaMapper.map(response)
                    )

                    val dadJokes = loadDadJokeFeedDB(cursor, limit)
                    if (dadJokes.isEmpty()) {
                        emit(Empty)
                    } else {
                        emit(Success(data = dadJokes))
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
