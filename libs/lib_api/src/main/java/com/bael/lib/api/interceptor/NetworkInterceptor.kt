package com.bael.lib.api.interceptor

import com.bael.lib.api.network.Network
import com.bael.lib.data.exception.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Singleton
internal class NetworkInterceptor @Inject constructor(
    network: Network
) : Interceptor,
    Network by network {

    override fun intercept(chain: Chain): Response {
        if (isConnected.not()) throw NoNetworkException()

        val request = chain.request()
        return chain.proceed(request)
    }
}
