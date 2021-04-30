package com.bael.dads.data.remote.mapper

import com.bael.dads.data.remote.model.DadJoke
import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.data.remote.query.DadJokesQuery.Joke as DadJokeQL

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokeMapper : Mapper<DadJokeQL, DadJoke> {

    override fun map(data: DadJokeQL): DadJoke {
        return data.fragments.dadJokeFragment.let { fragment ->
            DadJoke(
                id = fragment.id,
                setup = fragment.setup,
                punchline = fragment.punchline
            )
        }
    }
}
