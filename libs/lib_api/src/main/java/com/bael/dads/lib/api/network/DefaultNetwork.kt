@file:Suppress("DEPRECATION", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.bael.dads.lib.api.network

import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_MOBILE
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkInfo
import android.net.NetworkInfo.State.CONNECTED
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultNetwork @Inject constructor(@ApplicationContext context: Context) : Network {
    override val isConnected: Boolean
        get() = isNetworkConnected(TYPE_MOBILE) || isNetworkConnected(TYPE_WIFI)

    private val connectivityManager: ConnectivityManager by lazy {
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private fun isNetworkConnected(networkType: Int): Boolean {
        return networkInfo(networkType) == CONNECTED
    }

    private fun networkInfo(type: Int): NetworkInfo.State {
        return connectivityManager.getNetworkInfo(type).state
    }
}
