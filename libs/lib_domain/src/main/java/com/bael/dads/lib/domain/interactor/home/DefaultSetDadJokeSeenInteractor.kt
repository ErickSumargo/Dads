package com.bael.dads.lib.domain.interactor.home

import com.bael.dads.lib.domain.model.DadJoke
import com.bael.dads.lib.domain.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultSetDadJokeSeenInteractor @Inject constructor(
    private val repository: DadsRepository
) : SetDadJokeSeenInteractor {

    override operator fun invoke(dadJoke: DadJoke): Flow<Boolean> {
        return repository.setDadJokeSeen(dadJoke)
    }
}
