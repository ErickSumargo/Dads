package com.bael.dads.domain.home.interactor

import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.ObserveDadJokeUseCase
import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.shared.response.Response
import com.bael.dads.shared.response.Response.Success
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class ObserveDadJokeInteractor(
    private val repository: DadJokeRepository,
    private val mapper: Mapper<DadJokeDB, DadJoke>
) : ObserveDadJokeUseCase {

    override suspend fun invoke(dadJoke: DadJoke): Flow<Response<DadJoke>> {
        return repository.observeDadJoke(id = dadJoke.id)
            .filterNotNull()
            .map(mapper::map)
            .distinctUntilChanged()
            .map(::Success)
    }
}
