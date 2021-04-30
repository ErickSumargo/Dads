package com.bael.dads.data.remote.mapper

import com.bael.dads.data.remote.model.DadJoke
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.shared.mapper.ListMapper
import com.bael.dads.shared.mapper.Mapper
import com.bael.dads.data.remote.query.DadJokesQuery.Data as DadJokesQueryData
import com.bael.dads.data.remote.query.DadJokesQuery.Joke as DadJokeQL

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadJokesResponseMapper(
    private val dadJokesMapper: ListMapper<DadJokeQL, DadJoke>,
) : Mapper<DadJokesQueryData, DadJokesResponse> {

    override fun map(data: DadJokesQueryData): DadJokesResponse {
        return DadJokesResponse(
            dadJokes = dadJokesMapper.map(data.dadJokes.jokes),
            cursor = data.dadJokes.cursor
        )
    }
}
