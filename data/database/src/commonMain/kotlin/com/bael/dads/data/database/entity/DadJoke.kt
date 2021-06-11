package com.bael.dads.data.database.entity

/**
 * Created by ErickSumargo on 01/04/21.
 */

data class DadJoke(
    val id: Int = 0,
    val jokeId: String = "0",
    val setup: String = "",
    val punchline: String = "",
    val favored: Boolean = false,
    val seen: Boolean = false,
    val createdAt: Long = 0,
    val updatedAt: Long = 0
)
