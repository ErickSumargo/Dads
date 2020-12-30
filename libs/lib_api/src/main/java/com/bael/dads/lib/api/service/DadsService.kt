package com.bael.dads.lib.api.service

import com.bael.dads.lib.api.response.DadJokesResponse

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsService {

    suspend fun fetchDadJokes(
        cursor: String?,
        limit: Int
    ): Result<DadJokesResponse>
}
