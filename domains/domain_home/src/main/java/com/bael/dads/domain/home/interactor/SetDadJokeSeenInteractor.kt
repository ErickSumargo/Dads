package com.bael.dads.domain.home.interactor

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.SetDadJokeSeenUseCase
import com.bael.dads.lib.database.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class SetDadJokeSeenInteractor @Inject constructor(
    private val repository: DadsRepository
) : SetDadJokeSeenUseCase {

    override fun invoke(dadJoke: DadJoke): Flow<Boolean> {
        return flow {
            val updates = repository.setDadJokeSeen(
                id = dadJoke.id,
                updatedAt = System.currentTimeMillis()
            )
            emit(updates > 0)
        }
    }
}
