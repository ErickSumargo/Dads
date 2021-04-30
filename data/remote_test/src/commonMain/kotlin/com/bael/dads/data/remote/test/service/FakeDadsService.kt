package com.bael.dads.data.remote.test.service

import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.data.remote.service.DadsService

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class FakeDadsService :
    FakeBaseService<DadJokesResponse>(),
    DadsService {

    override suspend fun fetchDadJokes(cursor: String?, limit: Int): Result<DadJokesResponse> {
        return result
    }
}
