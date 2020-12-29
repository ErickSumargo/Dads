package com.bael.dads.lib.domain.mapper.facade

import com.bael.dads.lib.api.response.DadJokesResponse
import com.bael.dads.lib.data.mapper.ListMapper
import com.bael.dads.lib.data.mapper.Mapper
import com.bael.dads.lib.database.entity.RemoteMeta
import com.bael.dads.lib.domain.model.DadJoke
import javax.inject.Inject
import com.bael.dads.lib.api.model.DadJoke as DadJokeRemote
import com.bael.dads.lib.database.entity.DadJoke as DadJokeDB

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DadsMapper @Inject constructor(
    val dadJokesRemoteMapper: ListMapper<DadJokeRemote, DadJokeDB>,
    val dadJokesDBMapper: ListMapper<DadJokeDB, DadJoke>,
    val remoteMetaMapper: Mapper<DadJokesResponse, RemoteMeta>
)
