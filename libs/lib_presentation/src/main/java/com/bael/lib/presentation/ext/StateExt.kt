package com.bael.lib.presentation.ext

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun <S> S.reduce(reduceState: S.() -> S): S {
    return reduceState()
}
