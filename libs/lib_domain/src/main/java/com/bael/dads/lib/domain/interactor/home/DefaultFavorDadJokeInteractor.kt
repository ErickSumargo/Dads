package com.bael.dads.lib.domain.interactor.home

import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.domain.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultFavorDadJokeInteractor @Inject constructor(
    private val repository: DadsRepository
) : FavorDadJokeInteractor {

    override operator fun invoke(dadJoke: DadJoke, favored: Boolean): Flow<Boolean> {
        return repository.favorDadJoke(dadJoke, favored)
    }
}
