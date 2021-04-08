package com.bael.dads.lib.remote.service

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input.Companion.fromNullable
import com.bael.dads.domain.common.mapper.ResultMapper
import com.bael.dads.lib.remote.query.DadJokesQuery
import com.bael.dads.lib.remote.response.DadJokesResponse
import javax.inject.Inject
import com.bael.dads.lib.remote.query.DadJokesQuery.Data as DadJokesQueryData

/**
 * Created by ErickSumargo on 01/01/21.
 */

class DadsApolloService @Inject constructor(
    apollo: ApolloClient,
    private val mapper: ResultMapper<DadJokesQueryData, DadJokesResponse>
) : BaseApolloService(apollo),
    DadsService {

    override suspend fun fetchDadJokes(
        cursor: String?,
        limit: Int
    ): Result<DadJokesResponse> {
        return query(
            script = DadJokesQuery(
                cursor = fromNullable(cursor),
                limit = fromNullable(limit)
            )
        ).let(mapper::map)
    }
}
