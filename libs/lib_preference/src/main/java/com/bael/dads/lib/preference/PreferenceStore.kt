@file:Suppress("ApplySharedPref", "UNCHECKED_CAST")

package com.bael.dads.lib.preference

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class PreferenceStore @Inject constructor(
    @ApplicationContext context: Context
) : Store {
    private val preferences: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    }

    override fun <T> read(key: String, defaultValue: T): T {
        return try {
            when (defaultValue) {
                is Boolean -> {
                    preferences.getBoolean(key, defaultValue)
                }
                else -> {
                    throw UnsupportedOperationException()
                }
            } as T
        } catch (cause: Exception) {
            defaultValue
        }
    }

    override fun <T> write(key: String, value: T) {
        try {
            when (value) {
                is Boolean -> {
                    preferences.edit().also { editor ->
                        editor.putBoolean(key, value)
                        editor.commit()
                    }
                }
                else -> {
                    throw UnsupportedOperationException()
                }
            }
        } catch (cause: Exception) {
        }
    }
}
