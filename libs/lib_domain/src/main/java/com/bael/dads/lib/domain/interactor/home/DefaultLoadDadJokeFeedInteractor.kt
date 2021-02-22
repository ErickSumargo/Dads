package com.bael.dads.lib.domain.interactor.home

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.domain.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultLoadDadJokeFeedInteractor @Inject constructor(
    private val repository: DadsRepository
) : LoadDadJokeFeedInteractor {

    override operator fun invoke(
        cursor: DadJoke?,
        limit: Int
    ): Flow<Response<List<DadJoke>>> {
        return repository.loadDadJokeFeed(cursor, limit)
    }
}
