package com.bael.dads.lib.remote.test.service

import com.bael.dads.domain.common.response.Response
import com.bael.dads.domain.common.response.Response.Error
import com.bael.dads.domain.common.response.Response.Success
import java.io.IOException
import kotlin.Result.Companion.failure
import kotlin.Result.Companion.success

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal abstract class FakeBaseService<T> : RemoteService<T> {
    private var responses: Array<out Response<T>> = arrayOf()

    private var count: Int = 0

    protected val result: Result<T>
        get() {
            return when (val response = responses[count++]) {
                is Error -> failure(response.error as Throwable)
                is Success -> success(response.data)
                else -> failure(IOException("Connection error"))
            }
        }

    override fun submitResponses(vararg responses: Response<T>) {
        this.responses = responses
    }
}
