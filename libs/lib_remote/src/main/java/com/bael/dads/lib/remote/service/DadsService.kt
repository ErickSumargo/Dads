package com.bael.dads.lib.remote.service

import com.bael.dads.lib.remote.response.DadJokesResponse

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsService {

    suspend fun fetchDadJokes(cursor: String?, limit: Int): Result<DadJokesResponse>
}
