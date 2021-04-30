package com.bael.dads.domain.home.interactor

import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.LoadDadJokeUseCase
import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.shared.response.Response
import com.bael.dads.shared.response.Response.Loading
import com.bael.dads.shared.response.Response.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class LoadDadJokeInteractor(
    private val repository: DadJokeRepository,
    private val mapper: Mapper<DadJokeDB, DadJoke>
) : LoadDadJokeUseCase {

    override fun invoke(id: Int): Flow<Response<DadJoke>> {
        return flow {
            emit(Loading)

            repository.loadDadJoke(id)
                ?.let(mapper::map)
                ?.let(::Success)
                ?.also { response ->
                    emit(response)
                }
        }
    }
}
