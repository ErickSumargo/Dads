package com.bael.dads.lib.remote.test.service

import com.bael.dads.lib.remote.response.DadJokesResponse
import com.bael.dads.lib.remote.service.DadsService
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class FakeDadsService @Inject constructor() :
    FakeBaseService<DadJokesResponse>(),
    DadsService {

    override suspend fun fetchDadJokes(cursor: String?, limit: Int): Result<DadJokesResponse> {
        return result
    }
}
