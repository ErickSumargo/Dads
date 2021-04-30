package com.bael.dads.data.remote.test.service

import com.bael.dads.shared.response.Response

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface RemoteService<T> {

    fun submitResponses(vararg responses: Response<T>)
}
