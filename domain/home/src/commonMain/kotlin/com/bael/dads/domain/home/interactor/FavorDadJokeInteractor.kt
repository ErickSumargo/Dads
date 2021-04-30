package com.bael.dads.domain.home.interactor

import com.bael.dads.data.database.repository.DadJokeRepository
import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.FavorDadJokeUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class FavorDadJokeInteractor(
    private val repository: DadJokeRepository
) : FavorDadJokeUseCase {

    override fun invoke(dadJoke: DadJoke, favored: Boolean): Flow<Boolean> {
        return flow {
            val updates = repository.favorDadJoke(
                id = dadJoke.id,
                favored = favored
            )
            emit(updates > 0)
        }
    }
}
