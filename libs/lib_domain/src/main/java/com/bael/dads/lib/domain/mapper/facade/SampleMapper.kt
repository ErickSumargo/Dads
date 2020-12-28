package com.bael.dads.lib.domain.mapper.facade

import com.bael.dads.lib.data.mapper.ListMapper
import com.bael.dads.lib.domain.model.Sample
import javax.inject.Inject
import com.bael.dads.lib.database.entity.Sample as SampleDB

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class SampleMapper @Inject constructor(
    val samplesRemoteMapper: ListMapper<SampleRemote, SampleDB>,
    val samplesDBMapper: ListMapper<SampleDB, Sample>
)
