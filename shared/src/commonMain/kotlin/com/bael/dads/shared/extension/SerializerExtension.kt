package com.bael.dads.shared.extension

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by ErickSumargo on 01/05/21.
 */

inline fun <reified T> T.serialize(): String {
    return Json.encodeToString(value = this)
}

inline fun <reified T> String.deserialize(): T {
    return Json.decodeFromString(string = this)
}
