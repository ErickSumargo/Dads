package com.bael.dads.lib.data.response

/**
 * Created by ErickSumargo on 01/01/21.
 */

sealed class Response {

    object Loading : Response()

    data class Success<T>(val data: T) : Response()

    data class Error(val error: Exception) : Response()
}
