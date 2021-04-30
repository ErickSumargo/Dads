package com.bael.dads.data.remote.test.service

import com.bael.dads.shared.response.Response
import com.bael.dads.shared.response.Response.Error
import com.bael.dads.shared.response.Response.Success
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
                else -> failure(Exception("Connection error"))
            }
        }

    override fun submitResponses(vararg responses: Response<T>) {
        this.responses = responses
    }
}
