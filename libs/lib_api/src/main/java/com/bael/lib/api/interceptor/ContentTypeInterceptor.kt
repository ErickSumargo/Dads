package com.bael.lib.api.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Singleton
internal class ContentTypeInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Accept", "application/json")
        return chain.proceed(request.build())
    }
}
