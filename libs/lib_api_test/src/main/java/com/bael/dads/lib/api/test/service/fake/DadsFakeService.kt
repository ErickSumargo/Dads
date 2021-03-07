package com.bael.dads.lib.api.test.service.fake

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.NONE
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.api.service.DadsService
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

@VisibleForTesting(otherwise = NONE)
internal class DadsFakeService @Inject constructor() :
    BaseFakeService<DadJokesResponse>(),
    DadsService {
    override val defaultResponse: DadJokesResponse
        get() = DadJokesResponse(
            dadJokes = listOf(),
            cursor = null
        )

    override suspend fun fetchDadJokes(
        cursor: String?,
        limit: Int
    ): Result<DadJokesResponse> {
        return result
    }
}
