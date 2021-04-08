package com.bael.dads.domain.home.mapper

import com.bael.dads.domain.common.mapper.Mapper
import java.lang.System.currentTimeMillis
import javax.inject.Inject
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB
import com.bael.dads.lib.remote.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeRemoteMapper @Inject constructor() : Mapper<DadJokeRemote, DadJokeDB> {

    override fun map(data: DadJokeRemote): DadJokeDB {
        return DadJokeDB(
            jokeId = data.id,
            setup = data.setup,
            punchline = data.punchline,
            favored = false,
            seen = false,
            createdAt = currentTimeMillis(),
            updatedAt = currentTimeMillis()
        )
    }
}
