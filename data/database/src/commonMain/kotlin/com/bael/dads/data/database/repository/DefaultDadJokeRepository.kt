package com.bael.dads.data.database.repository

import com.bael.dads.data.database.DadsDatabase
import com.bael.dads.data.database.entity.DadJoke
import com.bael.dads.shared.time.DateTime.now
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultDadJokeRepository(database: DadsDatabase) :
    DadJokeRepository,
    DadsDatabase by database {

    override suspend fun insertDadJokes(dadJokes: List<DadJoke>) {
        dadJokeQueries.transaction {
            dadJokes.forEach { dadJoke ->
                dadJokeQueries.insertDadJoke(
                    jokeId = dadJoke.jokeId,
                    setup = dadJoke.setup,
                    punchline = dadJoke.punchline,
                    favored = dadJoke.favored,
                    seen = dadJoke.seen,
                    createdAt = now,
                    updatedAt = now
                )
            }
        }
    }

    override suspend fun loadDadJokeFeed(id: Int, limit: Int): List<DadJoke> {
        return dadJokeQueries.loadDadJokeFeed(id, limit.toLong())
            .executeAsList()
    }

    override suspend fun loadSeenDadJoke(term: String, cursor: Int, limit: Int): List<DadJoke> {
        return dadJokeQueries.loadSeenDadJoke(cursor, term, limit.toLong())
            .executeAsList()
    }

    override suspend fun loadFavoredDadJoke(term: String, cursor: Long, limit: Int): List<DadJoke> {
        return dadJokeQueries.loadFavoredDadJoke(updatedAt = cursor, term, limit.toLong())
            .executeAsList()
    }

    override suspend fun loadDadJoke(id: Int): DadJoke? {
        return dadJokeQueries.loadDadJoke(id)
            .executeAsOneOrNull()
    }

    override suspend fun loadLatestDadJoke(): DadJoke? {
        return dadJokeQueries.loadLatestDadJoke()
            .executeAsOneOrNull()
    }

    override suspend fun observeDadJoke(id: Int): Flow<DadJoke> {
        return dadJokeQueries.observeDadJoke(id)
            .asFlow()
            .mapToOneOrNull()
            .filterNotNull()
    }

    override suspend fun setDadJokeSeen(id: Int): Int {
        dadJokeQueries.setDadJokeSeen(updatedAt = now, id)
        return 1
    }

    override suspend fun favorDadJoke(id: Int, favored: Boolean): Int {
        dadJokeQueries.favorDadJoke(favored, updatedAt = now, id)
        return 1
    }

    override suspend fun deleteAllDadJokes(): Int {
        dadJokeQueries.deleteAllDadJokes()
        return 1
    }
}
