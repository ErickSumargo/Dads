package com.bael.lib.api.service.retrofit

import com.bael.lib.api.response.SampleResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal interface SampleService {

    @GET("sample")
    suspend fun fetchSample(): Response<SampleResponse>
}