package com.bael.dads.library.preference

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface Preference {

    suspend fun <T> read(key: String, defaultValue: T): T

    suspend fun <T> write(key: String, value: T)
}
