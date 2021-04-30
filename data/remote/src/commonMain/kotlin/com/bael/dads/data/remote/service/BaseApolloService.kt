package com.bael.dads.data.remote.service

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation.Data
import com.apollographql.apollo.api.Operation.Variables
import com.apollographql.apollo.api.Query
import kotlinx.coroutines.flow.first

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal abstract class BaseApolloService(private val client: ApolloClient) {

    protected suspend fun <D : Data> query(script: Query<D, D, Variables>): Result<D> {
        return runCatching {
            val response = client.query(script).execute().first()
            if (response.hasErrors()) {
                throw Exception(response.errors!![0].message)
            }
            response.data!!
        }
    }
}
