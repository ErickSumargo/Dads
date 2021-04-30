package com.bael.dads.library.presentation.ext

import com.bael.dads.library.presentation.state.BaseState

/**
 * Created by ErickSumargo on 01/01/21.
 */

fun <S : BaseState> S.reduce(reduceState: S.() -> S): S {
    return reduceState()
}
