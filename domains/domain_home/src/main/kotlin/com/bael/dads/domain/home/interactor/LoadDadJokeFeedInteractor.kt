package com.bael.dads.domain.home.interactor

import com.bael.dads.domain.common.mapper.ListMapper
import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.domain.common.response.Response
import com.bael.dads.domain.common.response.Response.Empty
import com.bael.dads.domain.common.response.Response.Error
import com.bael.dads.domain.common.response.Response.Loading
import com.bael.dads.domain.common.response.Response.Success
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.LoadDadJokeFeedUseCase
import com.bael.dads.lib.database.entity.RemoteMeta
import com.bael.dads.lib.database.repository.DadsRepository
import com.bael.dads.lib.database.repository.RemoteMetaRepository
import com.bael.dads.lib.remote.response.DadJokesResponse
import com.bael.dads.lib.remote.service.DadsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB
import com.bael.dads.lib.remote.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class LoadDadJokeFeedInteractor @Inject constructor(
    private val dadsRepository: DadsRepository,
    private val remoteMetaRepository: RemoteMetaRepository,
    private val service: DadsService,
    private val dadJokeDBMapper: Mapper<DadJokeDB, DadJoke>,
    private val dadJokesRemoteMapper: ListMapper<DadJokeRemote, DadJokeDB>,
    private val remoteMetaMapper: Mapper<DadJokesResponse, RemoteMeta>
) : LoadDadJokeFeedUseCase {

    override fun invoke(cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>> {
        return flow {
            emit(Loading)

            val dadJokes = loadDadJokeFeedDB(cursor, limit)
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
        return dadsRepository.loadDadJokeFeed(id = cursor?.id ?: 0, limit)
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
                    dadsRepository.insertDadJokes(
                        dadJokes = dadJokesRemoteMapper.map(response.dadJokes)
                    )

                    remoteMetaRepository.insertRemoteMeta(
                        meta = remoteMetaMapper.map(response)
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
