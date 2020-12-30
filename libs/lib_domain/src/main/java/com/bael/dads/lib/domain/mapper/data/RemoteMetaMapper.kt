package com.bael.dads.lib.domain.mapper.data

import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.data.mapper.Mapper
import com.bael.dads.lib.database.entity.RemoteMeta
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class RemoteMetaMapper @Inject constructor() : Mapper<DadJokesResponse, RemoteMeta> {

    override fun map(data: DadJokesResponse): RemoteMeta {
        return RemoteMeta(cursor = data.cursor)
    }
}
