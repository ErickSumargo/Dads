package com.bael.dads.domain.home.mapper

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.data.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeDBMapper : Mapper<DadJokeDB, DadJoke> {

    override fun map(data: DadJokeDB): DadJoke {
        return DadJoke(
            id = data.id,
            setup = data.setup,
            punchline = data.punchline,
            favored = data.favored,
            seen = data.seen,
            updatedAt = data.updatedAt
        )
    }
}
