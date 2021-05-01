package com.bael.dads.domain.home.mapper

import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.shared.time.DateTime.now
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB
import com.bael.dads.data.remote.model.DadJoke as DadJokeRemote

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeRemoteMapper : Mapper<DadJokeRemote, DadJokeDB> {

    override fun map(data: DadJokeRemote): DadJokeDB {
        return DadJokeDB(
            id = 0, // to be overridden by auto-increment key
            jokeId = data.id,
            setup = data.setup,
            punchline = data.punchline,
            favored = false,
            seen = false,
            createdAt = now,
            updatedAt = now
        )
    }
}
