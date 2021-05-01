package com.bael.dads.shared.response

/**
 * Created by ErickSumargo on 01/01/21.
 */

expect sealed class Response<out T> {

    object Loading : Response<Nothing>

    class Error(error: Exception) : Response<Nothing>

    object Empty : Response<Nothing>

    class Success<T>(data: T) : Response<T>
}
