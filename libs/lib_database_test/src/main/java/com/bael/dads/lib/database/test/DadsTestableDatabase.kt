package com.bael.dads.lib.database.test

import com.bael.dads.lib.database.DadsDatabase

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface DadsTestableDatabase : DadsDatabase {

    fun close()
}
