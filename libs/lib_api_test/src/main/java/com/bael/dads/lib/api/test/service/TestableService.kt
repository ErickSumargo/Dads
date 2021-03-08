package com.bael.dads.lib.api.test.service

import com.bael.dads.lib.data.response.Response

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface TestableService<T> {

    fun submitResponse(response: Response<T>)

    fun clear()
}
