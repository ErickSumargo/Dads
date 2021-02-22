package com.bael.dads.lib.preference

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface Store {

    fun <T> read(key: String, defaultValue: T): T

    fun <T> write(key: String, value: T)
}
