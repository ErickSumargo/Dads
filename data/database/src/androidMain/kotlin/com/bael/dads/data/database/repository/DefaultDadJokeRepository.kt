package com.bael.dads.data.database.repository

import com.bael.dads.data.database.entity.DadJoke
import com.bael.dads.shared.time.DateTime.now
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultDadJokeRepository(
    private val database: DatabaseReference
) : DadJokeRepository {

    override suspend fun insertDadJokes(dadJokes: List<DadJoke>) {
        database.child("dadJoke").also { table ->
            dadJokes.forEach { dadJoke ->
                table.child(dadJoke.jokeId).setValue(dadJoke)
            }
        }
    }

    override suspend fun loadDadJokeFeed(id: Int, limit: Int): List<DadJoke> {
        return suspendCoroutine { continuation ->
            database.child("dadJoke").get()
                .addOnSuccessListener { dataSnapshot ->
                    continuation.resume(
                        dataSnapshot.children.map { child ->
                            child.getValue(DadJoke::class.java)!!
                        }.filter { dadJoke ->
                            dadJoke.id > id
                        }.take(limit)
                    )
                }
        }
    }

    override suspend fun loadSeenDadJoke(term: String, cursor: Int, limit: Int): List<DadJoke> {
        return suspendCoroutine { continuation ->
            database.child("dadJoke").get()
                .addOnSuccessListener { dataSnapshot ->
                    continuation.resume(
                        dataSnapshot.children.map { child ->
                            child.getValue(DadJoke::class.java)!!
                        }.filter { dadJoke ->
                            dadJoke.id < cursor && dadJoke.setup.contains(term) && dadJoke.seen
                        }.sortedByDescending { dadJoke ->
                            dadJoke.id
                        }.take(limit)
                    )
                }
        }
    }

    override suspend fun loadFavoredDadJoke(term: String, cursor: Long, limit: Int): List<DadJoke> {
        return suspendCoroutine { continuation ->
            database.child("dadJoke").get()
                .addOnSuccessListener { dataSnapshot ->
                    continuation.resume(
                        dataSnapshot.children.map { child ->
                            child.getValue(DadJoke::class.java)!!
                        }.filter { dadJoke ->
                            dadJoke.updatedAt < cursor && dadJoke.setup.contains(term) && dadJoke.favored
                        }.sortedByDescending { dadJoke ->
                            dadJoke.updatedAt
                        }.take(limit)
                    )
                }
        }
    }

    override suspend fun loadDadJoke(id: Int): DadJoke? {
        return suspendCoroutine { continuation ->
            database.child("dadJoke").get()
                .addOnSuccessListener { dataSnapshot ->
                    continuation.resume(
                        dataSnapshot.children.map { child ->
                            child.getValue(DadJoke::class.java)!!
                        }.firstOrNull { dadJoke ->
                            dadJoke.id == id
                        }
                    )
                }
        }
    }

    override suspend fun loadLatestDadJoke(): DadJoke? {
        return suspendCoroutine { continuation ->
            database.child("dadJoke").get()
                .addOnSuccessListener { dataSnapshot ->
                    continuation.resume(
                        dataSnapshot.children.map { child ->
                            child.getValue(DadJoke::class.java)!!
                        }.lastOrNull()
                    )
                }
        }
    }

    override suspend fun observeDadJoke(id: Int): Flow<DadJoke> {
        return suspendCoroutine { continuation ->
            database.child("dadJoke").get()
                .addOnSuccessListener { dataSnapshot ->
                    continuation.resume(
                        flow {
                            dataSnapshot.children.map { child ->
                                child.getValue(DadJoke::class.java)!!
                            }.firstOrNull { dadJoke ->
                                dadJoke.id == id
                            }
                        }
                    )
                }
        }
    }

    override suspend fun setDadJokeSeen(id: Int): Int {
        database.child("dadJoke").child("$id").get()
            .addOnSuccessListener { dataSnapshot ->
                val dadJoke = dataSnapshot.getValue(DadJoke::class.java)!!
                database.child("dadJoke")
                    .child("$id")
                    .setValue(dadJoke.copy(seen = true, updatedAt = now))
            }
        return 1
    }

    override suspend fun favorDadJoke(id: Int, favored: Boolean): Int {
        database.child("dadJoke").child("$id").get()
            .addOnSuccessListener { dataSnapshot ->
                val dadJoke = dataSnapshot.getValue(DadJoke::class.java)!!
                database.child("dadJoke")
                    .child("$id")
                    .setValue(dadJoke.copy(favored = favored, updatedAt = now))
            }
        return 1
    }

    override suspend fun deleteAllDadJokes(): Int {
        database.child("dadJoke").removeValue()
        return 1
    }
}
