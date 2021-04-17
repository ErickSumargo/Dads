package com.bael.dads.domain.home.interactor

import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.domain.common.response.Response
import com.bael.dads.domain.common.response.Response.Loading
import com.bael.dads.domain.common.response.Response.Success
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.LoadDadJokeUseCase
import com.bael.dads.lib.database.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class LoadDadJokeInteractor @Inject constructor(
    private val repository: DadsRepository,
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
