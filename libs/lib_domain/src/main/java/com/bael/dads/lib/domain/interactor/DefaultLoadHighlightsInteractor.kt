package com.bael.dads.lib.domain.interactor

import com.bael.dads.lib.data.response.Response
import com.bael.dads.lib.domain.repository.DadsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by ErickSumargo on 01/01/21.
 */

internal class DefaultLoadHighlightsInteractor @Inject constructor(
    private val dadJokeRepository: DadsRepository
) : LoadHighlightsInteractor {

    override suspend operator fun invoke(
        seen: Boolean,
        limit: Int
    ): Flow<Response> {
        return dadJokeRepository.loadHighlights(seen, limit)
    }
}
