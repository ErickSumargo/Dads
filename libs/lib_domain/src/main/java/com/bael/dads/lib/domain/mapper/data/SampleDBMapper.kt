package com.bael.dads.lib.domain.mapper.data

import com.bael.dads.lib.data.mapper.Mapper
import com.bael.dads.lib.domain.model.Sample
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class SampleDBMapper @Inject constructor() : Mapper<SampleDB, Sample> {

    override fun map(data: SampleDB): Sample {
        return Sample(id = data.id)
    }
}
