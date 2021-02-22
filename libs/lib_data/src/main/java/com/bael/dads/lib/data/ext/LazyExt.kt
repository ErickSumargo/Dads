package com.bael.dads.lib.data.ext

/**
 * Created by ErickSumargo on 01/01/21.
 */

operator fun <T> Lazy<T>.invoke(): T = value
