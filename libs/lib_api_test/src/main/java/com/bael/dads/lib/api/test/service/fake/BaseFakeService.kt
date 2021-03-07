package com.bael.dads.lib.api.test.service.fake

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.data.response.Response.Error
import com.bael.dads.lib.data.response.Response.Success
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

/**
 * Created by ErickSumargo on 01/01/21.
 */

abstract class BaseFakeService<T> {
    private var response: Response<T>? = null

    internal abstract val defaultResponse: T

    protected val result: Result<T>
        get() {
            return when (response) {
                is Error -> failure((response as Error).error as Throwable)
                is Success -> success((response as Success).data)
                else -> success(defaultResponse)
            }
        }

    fun submitResponse(response: Response<T>) {
        this.response = response
    }

    fun clear() {
        response = null
    }
}
