package com.bael.dads.lib.api.interceptor

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Singleton
internal class AuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header("Authorization", "Bearer [TOKEN]")
        return chain.proceed(request.build())
    }
}