package com.bael.dads.domain.home.mapper

import com.bael.dads.data.database.entity.RemoteMeta
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.shared.mapper.Mapper

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class RemoteMetaMapper : Mapper<DadJokesResponse, RemoteMeta> {

    override fun map(data: DadJokesResponse): RemoteMeta {
        return RemoteMeta(
            id = 0,
            cursor = data.cursor
        )
    }
}
