package com.bael.dads.domain.home.interactor

import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.domain.common.response.Response
import com.bael.dads.domain.common.response.Response.Success
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.ObserveDadJokeUseCase
import com.bael.dads.lib.database.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class ObserveDadJokeInteractor @Inject constructor(
    private val repository: DadsRepository,
    private val mapper: Mapper<DadJokeDB, DadJoke>
) : ObserveDadJokeUseCase {

    override fun invoke(dadJoke: DadJoke): Flow<Response<DadJoke>> {
        return repository
            .observeDadJoke(id = dadJoke.id)
            .filterNotNull()
            .map(mapper::map)
            .distinctUntilChanged()
            .map(::Success)
    }
}
