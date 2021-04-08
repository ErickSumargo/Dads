package com.bael.dads.domain.home.mapper

import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.domain.home.model.DadJoke
import javax.inject.Inject
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeDBMapper @Inject constructor() : Mapper<DadJokeDB, DadJoke> {

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
