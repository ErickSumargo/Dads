package com.bael.dads.library.preference

import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface Preference {

    fun <T> read(key: String, defaultValue: T): Flow<T>

    suspend fun <T> write(key: String, value: T)
}
