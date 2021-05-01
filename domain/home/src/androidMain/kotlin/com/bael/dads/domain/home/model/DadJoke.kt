package com.bael.dads.domain.home.model

import java.io.Serializable

/**
 * Created by ErickSumargo on 01/05/21.
 */

actual data class DadJoke actual constructor(
    val id: Int,
    val setup: String,
    val punchline: String,
    val favored: Boolean,
    val seen: Boolean,
    val updatedAt: Long
) : Serializable
