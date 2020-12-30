package com.bael.dads.lib.domain.mapper.data

import com.bael.dads.lib.data.mapper.Mapper
import com.bael.dads.lib.domain.model.DadJoke
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
            punchline = data.punchline
        )
    }
}
