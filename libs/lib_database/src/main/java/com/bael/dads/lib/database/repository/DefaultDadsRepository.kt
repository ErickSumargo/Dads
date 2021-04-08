package com.bael.dads.lib.database.repository

import com.bael.dads.lib.database.DadsDatabase
import com.bael.dads.lib.database.dao.DadJokeDao
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultDadsRepository @Inject constructor(database: DadsDatabase) :
    DadsRepository,
    DadJokeDao by database.dadJoke
