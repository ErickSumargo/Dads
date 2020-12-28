package com.bael.dads.lib.domain.mapper.data

import com.bael.dads.lib.data.mapper.Mapper
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class SampleRemoteMapper @Inject constructor() : Mapper<SampleRemote, SampleDB> {

    override fun map(data: SampleRemote): SampleDB {
        return SampleDB(id = data.id)
    }
}
