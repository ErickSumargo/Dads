package com.bael.dads.lib.domain.interactor.home

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.domain.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultLoadDadJokeInteractor @Inject constructor(
    private val repository: DadsRepository
) : LoadDadJokeInteractor {

    override operator fun invoke(id: Int): Flow<Response<DadJoke>> {
        return repository.loadDadJoke(id)
    }
}
