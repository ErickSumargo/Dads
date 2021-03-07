package com.bael.dads.lib.api.service.apollo

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input.Companion.fromNullable
import com.bael.dads.lib.api.query.DadJokesQuery
import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.api.service.DadsService
import com.bael.dads.lib.data.mapper.ResultMapper
import javax.inject.Inject
import com.bael.dads.lib.api.query.DadJokesQuery.Data as DadJokesQueryData

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
