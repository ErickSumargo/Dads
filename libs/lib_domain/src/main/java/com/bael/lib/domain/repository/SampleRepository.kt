package com.bael.lib.domain.repository

import com.bael.lib.domain.model.Response
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal interface SampleRepository {

    suspend fun loadSamples(): Flow<Response>
}
