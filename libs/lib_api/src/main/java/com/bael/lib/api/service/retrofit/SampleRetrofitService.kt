package com.bael.dads.lib.api.service.retrofit

import com.bael.dads.lib.api.response.SampleResponse
import com.bael.dads.lib.api.service.SampleService
import javax.inject.Inject
import com.bael.dads.lib.api.service.retrofit.SampleService as RetrofitSampleService

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class SampleRetrofitService @Inject constructor(
    private val service: RetrofitSampleService
) : BaseRetrofitService(),
    SampleService {

    override suspend fun fetchSample(): Result<SampleResponse> {
        return request { service.fetchSample() }
    }
}
