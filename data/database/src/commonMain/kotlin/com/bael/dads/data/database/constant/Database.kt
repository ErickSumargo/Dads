package com.bael.dads.data.database.constant

import com.squareup.sqldelight.db.SqlDriver.Schema
import com.bael.dads.data.database.DadsDatabase.Companion.Schema as DadsDatabaseSchema

/**
 * Created by ErickSumargo on 01/05/21.
 */

internal object Database {
    const val name: String = "dads.db"

    val schema: Schema
        get() = DadsDatabaseSchema
}
