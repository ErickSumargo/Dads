package com.bael.dads.lib.remote.mapper.data

import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.lib.remote.model.DadJoke
import javax.inject.Inject
import com.bael.dads.lib.remote.query.DadJokesQuery.Joke as DadJokeClient

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeClientMapper @Inject constructor() : Mapper<DadJokeClient, DadJoke> {

    override fun map(data: DadJokeClient): DadJoke {
        return data.fragments.dadJokeFragment.let { fragment ->
            DadJoke(
                id = fragment.id,
                setup = fragment.setup,
                punchline = fragment.punchline
            )
        }
    }
}
