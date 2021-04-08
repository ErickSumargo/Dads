package com.bael.dads.domain.home.mapper

import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.lib.database.entity.RemoteMeta
import com.bael.dads.lib.remote.response.DadJokesResponse
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class RemoteMetaMapper @Inject constructor() : Mapper<DadJokesResponse, RemoteMeta> {

    override fun map(data: DadJokesResponse): RemoteMeta {
        return RemoteMeta(cursor = data.cursor)
    }
}
