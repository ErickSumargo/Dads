package com.bael.dads.lib.domain.interactor.home

import com.bael.dads.lib.domain.model.DadJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by ErickSumargo on 01/01/21.
 */

interface FavorDadJokeInteractor {

    operator fun invoke(dadJoke: DadJoke, favored: Boolean): Flow<Boolean>
}
