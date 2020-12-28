package com.bael.lib.database.di.qualifier

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.BINARY

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Qualifier
@Retention(BINARY)
internal annotation class DatabaseNameQualifier
