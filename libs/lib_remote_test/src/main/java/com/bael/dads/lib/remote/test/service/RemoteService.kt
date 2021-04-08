package com.bael.dads.lib.remote.test.service

import com.bael.dads.domain.common.response.Response

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface RemoteService<T> {

    fun submitResponse(response: Response<T>)
}
