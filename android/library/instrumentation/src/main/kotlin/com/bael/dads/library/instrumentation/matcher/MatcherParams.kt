package com.bael.dads.library.instrumentation.matcher

/**
 * Created by ErickSumargo on 15/05/21.
 */

data class MatcherParams(
    val id: Int = -1,
    val text: String = "",
    val parent: MatcherParams? = null,
    val sibling: MatcherParams? = null
)
