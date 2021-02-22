package com.bael.dads.lib.data.ext

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.data.response.Response.Success

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun <T> List<Response<T>>.findSuccess(): Success<T>? {
    return firstOrNull { it is Success } as? Success
}
