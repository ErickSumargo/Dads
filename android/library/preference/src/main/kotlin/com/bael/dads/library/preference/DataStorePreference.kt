@file:Suppress("UNCHECKED_CAST")

package com.bael.dads.library.preference

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.bael.dads.library.threading.Thread
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

class DataStorePreference @Inject constructor(
    @ApplicationContext private val context: Context,
    private val thread: Thread
) : Preference {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = context.packageName
    )

    override suspend fun <T> read(key: String, defaultValue: T): T {
        return withContext(context = thread.io) {
            val preferencesKey = when (defaultValue) {
                is Int -> intPreferencesKey(key)
                is Double -> doublePreferencesKey(key)
                is String -> stringPreferencesKey(key)
                is Boolean -> booleanPreferencesKey(key)
                is Float -> floatPreferencesKey(key)
                is Long -> longPreferencesKey(key)
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
                    is Int -> {
                        val preferencesKey = intPreferencesKey(key)
                        preferences[preferencesKey] = value
                    }
                    is Double -> {
                        val preferencesKey = doublePreferencesKey(key)
                        preferences[preferencesKey] = value
                    }
                    is String -> {
                        val preferencesKey = stringPreferencesKey(key)
                        preferences[preferencesKey] = value
                    }
                    is Boolean -> {
                        val preferencesKey = booleanPreferencesKey(key)
                        preferences[preferencesKey] = value
                    }
                    is Float -> {
                        val preferencesKey = floatPreferencesKey(key)
                        preferences[preferencesKey] = value
                    }
                    is Long -> {
                        val preferencesKey = longPreferencesKey(key)
                        preferences[preferencesKey] = value
                    }
                    else -> {
                        val preferencesKey = stringPreferencesKey(key)
                        preferences[preferencesKey] = value.toString()
                    }
                }
            }
        }
    }
}
