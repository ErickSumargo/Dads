package com.bael.dads.domain.home.model

/**
 * Created by ErickSumargo on 01/01/21.
 */

expect class DadJoke(
    id: Int,
    setup: String,
    punchline: String,
    favored: Boolean,
    seen: Boolean,
    updatedAt: Long
)
