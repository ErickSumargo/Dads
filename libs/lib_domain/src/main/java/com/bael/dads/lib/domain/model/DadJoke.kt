package com.bael.dads.lib.domain.model

import java.io.Serializable

/**
 * Created by ErickSumargo on 01/01/21.
 */

data class DadJoke(
    val id: Int,
    val setup: String,
    val punchline: String,
    val favored: Boolean,
    val seen: Boolean,
    val updatedAt: Long
) : Serializable
