package com.bael.lib.api.service

import com.bael.lib.api.response.SampleResponse

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface SampleService {

    suspend fun fetchSample(): Result<SampleResponse>
}
