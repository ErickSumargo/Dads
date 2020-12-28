package com.bael.dads.lib.domain.di.module

import com.bael.dads.lib.data.mapper.Mapper
import com.bael.dads.lib.domain.mapper.data.SampleDBMapper
import com.bael.dads.lib.domain.mapper.data.SampleRemoteMapper
import com.bael.dads.lib.domain.model.Sample
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import com.bael.dads.lib.database.entity.Sample as SampleDB

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Module
@InstallIn(ApplicationComponent::class)
internal abstract class MapperModule {

    @Binds
    @Singleton
    internal abstract fun bindSampleRemoteMapper(
        mapper: SampleRemoteMapper
    ): Mapper<SampleRemote, SampleDB>

    @Binds
    @Singleton
    internal abstract fun bindSampleDBMapper(
        mapper: SampleDBMapper
    ): Mapper<SampleDB, Sample>
}
