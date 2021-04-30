package com.bael.dads.data.remote.interceptor

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.interceptor.ApolloInterceptorChain
import com.apollographql.apollo.interceptor.ApolloRequest
import com.apollographql.apollo.interceptor.ApolloRequestInterceptor
import com.apollographql.apollo.interceptor.ApolloResponse
import com.bael.dads.data.remote.network.Network
import com.bael.dads.shared.exception.NoNetworkException
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/05/21.
 */

internal class NetworkInterceptor(private val network: Network) : ApolloRequestInterceptor {

    override fun <D : Operation.Data> intercept(
        request: ApolloRequest<D>,
        chain: ApolloInterceptorChain
    ): Flow<ApolloResponse<D>> {
        if (!network.isConnected) throw NoNetworkException()
        return chain.proceed(request)
    }
}
