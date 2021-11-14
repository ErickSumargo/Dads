package com.bael.dads

import android.os.StrictMode.ThreadPolicy
import android.os.StrictMode.VmPolicy
import android.os.StrictMode.setThreadPolicy
import android.os.StrictMode.setVmPolicy
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by ErickSumargo on 01/01/21.
 */

@HiltAndroidApp
internal class DadsAndroidDebugApplication : DadsAndroidApplication() {

    override fun onCreate() {
        super.onCreate()
        setStrictMode()
    }

    private fun setStrictMode() {
        setThreadPolicy(
            ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        setVmPolicy(
            VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )
    }
}
