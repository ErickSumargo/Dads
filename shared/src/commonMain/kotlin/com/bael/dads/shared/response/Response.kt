package com.bael.dads.shared.response

/**
 * Created by ErickSumargo on 01/01/21.
 */

sealed class Response<out T> {

    object Loading : Response<Nothing>()

    data class Error(val error: Exception) : Response<Nothing>()

    object Empty : Response<Nothing>()

    data class Success<T>(val data: T) : Response<T>()
}
