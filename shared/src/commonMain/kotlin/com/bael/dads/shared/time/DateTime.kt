package com.bael.dads.shared.time

import kotlinx.datetime.Clock.System.now

/**
 * Created by ErickSumargo on 01/05/21.
 */

object DateTime {
    val now: Long
        get() = now().toEpochMilliseconds()
}
