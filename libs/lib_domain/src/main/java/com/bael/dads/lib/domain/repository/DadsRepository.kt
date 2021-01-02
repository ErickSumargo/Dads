package com.bael.dads.lib.domain.repository

import com.bael.dads.lib.data.response.Response
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsRepository {

    suspend fun loadHighlights(limit: Int): Flow<Response>
}
