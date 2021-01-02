package com.bael.dads.lib.domain.interactor.home

import com.bael.dads.lib.data.response.Response
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

typealias LoadHighlights = suspend (limit: Int) -> Flow<Response>
