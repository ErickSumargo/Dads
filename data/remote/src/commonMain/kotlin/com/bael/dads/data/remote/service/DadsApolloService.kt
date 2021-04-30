package com.bael.dads.data.remote.service

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input.Companion.fromNullable
import com.bael.dads.data.remote.query.DadJokesQuery
import com.bael.dads.data.remote.response.DadJokesResponse
import com.bael.dads.shared.mapper.ResultMapper
import com.bael.dads.data.remote.query.DadJokesQuery.Data as DadJokesQueryData

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadsApolloService(
    client: ApolloClient,
    private val mapper: ResultMapper<DadJokesQueryData, DadJokesResponse>
) : BaseApolloService(client),
    DadsService {

    override suspend fun fetchDadJokes(cursor: String?, limit: Int): Result<DadJokesResponse> {
        return query(
            script = DadJokesQuery(
                cursor = fromNullable(cursor),
                limit = fromNullable(limit)
            )
        ).let(mapper::map)
    }
}
