package com.bael.dads.lib.api.mapper.data

import com.bael.dads.lib.api.model.DadJoke
import com.bael.dads.lib.data.mapper.Mapper
import javax.inject.Inject
import com.bael.dads.lib.api.query.DadJokesQuery.Joke as DadJokeClient

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
