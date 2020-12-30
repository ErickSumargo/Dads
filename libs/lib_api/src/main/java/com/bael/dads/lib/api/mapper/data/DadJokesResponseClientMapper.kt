package com.bael.dads.lib.api.mapper.data

import com.bael.dads.lib.api.model.DadJoke
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.data.mapper.ListMapper
import com.bael.dads.lib.data.mapper.Mapper
import javax.inject.Inject
import com.bael.dads.lib.api.query.DadJokesQuery.Data as DadJokesQueryData
import com.bael.dads.lib.api.query.DadJokesQuery.Joke as DadJokeClient

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
