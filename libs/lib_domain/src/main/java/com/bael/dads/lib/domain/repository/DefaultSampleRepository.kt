package com.bael.dads.lib.domain.repository

import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.domain.mapper.facade.SampleMapper
import com.bael.dads.lib.domain.model.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultSampleRepository @Inject constructor(
    private val api: DadsService,
    private val database: DadsDatabase,
    private val mapper: SampleMapper
) : SampleRepository {

    override suspend fun loadSamples(): Flow<Response> {
        TODO("Not yet implemented")
    }
}
