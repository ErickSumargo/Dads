package com.bael.lib.domain.mapper.data

import com.bael.lib.data.mapper.Mapper
import com.bael.lib.domain.model.Sample
import javax.inject.Inject
import com.bael.lib.database.entity.Sample as SampleDB

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class SampleDBMapper @Inject constructor() : Mapper<SampleDB, Sample> {

    override fun map(data: SampleDB): Sample {
        return Sample(id = data.id)
    }
}
