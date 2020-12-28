package com.bael.dads.lib.domain.interactor

import com.bael.dads.lib.domain.model.Response
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface SampleInteractor {

    suspend operator fun invoke(): Flow<Response>
}
