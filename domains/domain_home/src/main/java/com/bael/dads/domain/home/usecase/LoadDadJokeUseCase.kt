package com.bael.dads.domain.home.usecase

import com.bael.dads.domain.common.response.Response
import com.bael.dads.domain.home.model.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/04/21.
 */

interface LoadDadJokeUseCase {

    operator fun invoke(id: Int): Flow<Response<DadJoke>>
}
