@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.lib.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bael.dads.lib.threading.Thread
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DataStorePreference @Inject constructor(
    @ApplicationContext private val context: Context,
    private val thread: Thread
) : Preference {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = context.packageName
    )

    override suspend fun <T> read(key: String, defaultValue: T): T {
        return withContext(context = thread.io) {
            val preferencesKey = when (defaultValue) {
                is Boolean -> booleanPreferencesKey(key)
                else -> stringPreferencesKey(key)
            }
            val preferences = context.dataStore.data.first()

            preferences[preferencesKey] as? T ?: defaultValue
        }
    }

    override suspend fun <T> write(key: String, value: T) {
        withContext(context = thread.io) {
            context.dataStore.edit { preferences ->
                when (value) {
                    is Boolean -> {
                        val preferencesKey = booleanPreferencesKey(key)
                        preferences[preferencesKey] = value
                    }
                }
            }
        }
    }
}
