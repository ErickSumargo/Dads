package com.bael.dads.domain.home.model

import kotlinx.serialization.Serializable

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Serializable
data class DadJoke(
    val id: Int,
    val setup: String,
    val punchline: String,
    val favored: Boolean,
    val seen: Boolean,
    val updatedAt: Long
)
