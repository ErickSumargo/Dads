package com.bael.dads.domain.home.interactor

import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.SetDadJokeSeenUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class SetDadJokeSeenInteractor(
    private val repository: DadJokeRepository
) : SetDadJokeSeenUseCase {

    override fun invoke(dadJoke: DadJoke): Flow<Boolean> {
        return flow {
            val updates = repository.setDadJokeSeen(id = dadJoke.id)
            emit(updates > 0)
        }
    }
}
