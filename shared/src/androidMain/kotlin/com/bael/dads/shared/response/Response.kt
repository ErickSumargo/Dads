package com.bael.dads.shared.response

import java.io.Serializable

/**
 * Created by ErickSumargo on 01/05/21.
 */

actual sealed class Response<out T> : Serializable {

    actual object Loading : Response<Nothing>()

    actual data class Error actual constructor(
        val error: Exception
    ) : Response<Nothing>()

    actual object Empty : Response<Nothing>()

    actual data class Success<T> actual constructor(
        val data: T
    ) : Response<T>()
}
