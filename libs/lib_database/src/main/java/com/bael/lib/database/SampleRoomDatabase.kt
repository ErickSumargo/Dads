package com.bael.dads.lib.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bael.dads.lib.database.entity.Sample

/**
 * Created by ErickSumargo on 01/01/21.
 */

@Database(entities = [Sample::class], version = 1)
internal abstract class SampleRoomDatabase : RoomDatabase(), SampleDatabase
