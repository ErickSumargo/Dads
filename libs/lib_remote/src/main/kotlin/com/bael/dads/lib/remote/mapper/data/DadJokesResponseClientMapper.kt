package com.bael.dads.lib.remote.mapper.data

import com.bael.dads.domain.common.mapper.ListMapper
import com.bael.dads.domain.common.mapper.Mapper
import com.bael.dads.lib.remote.model.DadJoke
import com.bael.dads.lib.remote.response.DadJokesResponse
import javax.inject.Inject
import com.bael.dads.lib.remote.query.DadJokesQuery.Data as DadJokesQueryData
import com.bael.dads.lib.remote.query.DadJokesQuery.Joke as DadJokeClient

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokesResponseClientMapper @Inject constructor(
    private val dadJokesMapper: ListMapper<DadJokeClient, DadJoke>,
) : Mapper<DadJokesQueryData, DadJokesResponse> {

    override fun map(data: DadJokesQueryData): DadJokesResponse {
        return DadJokesResponse(
            dadJokes = dadJokesMapper.map(data.dadJokes.jokes),
            cursor = data.dadJokes.cursor
        )
    }
}
