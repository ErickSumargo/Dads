@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.api.service.apollo

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation.Data
import com.apollographql.apollo.api.Operation.Variables
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.coroutines.await
import java.io.IOException

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal abstract class BaseApolloService(private val client: ApolloClient) {

    protected suspend fun <D : Data> query(script: Query<D, D, Variables>): Result<D> {
        return runCatching {
            val response = client.query(script).await()
            if (response.hasErrors()) {
                throw IOException(response.errors!![0].message)
            }
            response.data!!
        }
    }
}
