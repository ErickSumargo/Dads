package com.bael.dads.data.database.test.di.module

import com.bael.dads.data.database.di.module.DatabaseModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
internal object DatabaseTestModule
