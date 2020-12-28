package com.bael.dads.lib.api.service.retrofit

import retrofit2.Response
import java.io.IOException

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal abstract class BaseRetrofitService {

    protected suspend inline fun <T> request(
        crossinline block: suspend () -> Response<T>
    ): Result<T> {
        return runCatching {
            val response = block()
            if (!response.isSuccessful) {
                throw IOException(response.message())
            }
            response.body()!!
        }
    }
}
