package com.bael.dads.lib.domain.repository

import com.bael.dads.lib.domain.model.Response
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal interface SampleRepository {

    suspend fun loadSamples(): Flow<Response>
}
