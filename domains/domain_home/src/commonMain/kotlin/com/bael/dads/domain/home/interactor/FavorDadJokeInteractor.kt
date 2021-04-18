package com.bael.dads.domain.home.interactor

import com.bael.dads.domain.home.model.DadJoke
import com.bael.dads.domain.home.usecase.FavorDadJokeUseCase
import com.bael.dads.lib.database.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.System.currentTimeMillis
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class FavorDadJokeInteractor @Inject constructor(
    private val repository: DadsRepository
) : FavorDadJokeUseCase {

    override fun invoke(dadJoke: DadJoke, favored: Boolean): Flow<Boolean> {
        return flow {
            val updates = repository.favorDadJoke(
                id = dadJoke.id,
                favored = favored,
                updatedAt = currentTimeMillis()
            )
            emit(updates > 0)
        }
    }
}
