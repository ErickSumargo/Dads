@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.library.preference.test

import com.bael.dads.library.preference.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class FakePreference @Inject constructor() : Preference {
    private val container: MutableMap<String, Any> = HashMap()

    override fun <T> read(key: String, defaultValue: T): Flow<T> {
        return flow {
            emit(container[key] as? T ?: defaultValue)
        }
    }

    override suspend fun <T> write(key: String, value: T) {
        container[key] = value as Any
    }
}
