package com.bael.dads.domain.home.usecase

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.shared.response.Response
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface LoadDadJokeFeedUseCase {

    operator fun invoke(cursor: DadJoke?, limit: Int): Flow<Response<List<DadJoke>>>
}
