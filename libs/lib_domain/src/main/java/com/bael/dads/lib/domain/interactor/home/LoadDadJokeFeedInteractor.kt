package com.bael.dads.lib.domain.interactor.home

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.domain.model.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface LoadDadJokeFeedInteractor {

    operator fun invoke(cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>>
}
