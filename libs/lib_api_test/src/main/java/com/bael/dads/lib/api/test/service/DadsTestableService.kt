package com.bael.dads.lib.api.test.service

import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.data.response.Response

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsTestableService<T> : DadsService {

    fun submitResponse(response: Response<T>)

    fun clear()
}
