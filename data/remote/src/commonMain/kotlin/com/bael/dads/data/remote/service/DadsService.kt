package com.bael.dads.data.remote.service

import com.bael.dads.data.remote.response.DadJokesResponse

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsService {

    suspend fun fetchDadJokes(cursor: String?, limit: Int): Result<DadJokesResponse>
}
