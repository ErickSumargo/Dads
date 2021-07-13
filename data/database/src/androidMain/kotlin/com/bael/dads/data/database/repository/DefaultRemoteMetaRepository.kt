package com.bael.dads.data.database.repository

import com.bael.dads.data.database.entity.RemoteMeta
import com.google.firebase.database.DatabaseReference
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by ErickSumargo on 01/04/21.
 */

internal class DefaultRemoteMetaRepository(
    private val database: DatabaseReference
) : RemoteMetaRepository {

    override suspend fun loadRemoteMeta(): RemoteMeta? {
        return suspendCoroutine { continuation ->
            database.child("remoteMeta").get()
                .addOnSuccessListener { dataSnapshot ->
                    continuation.resume(
                        dataSnapshot.children.map { child ->
                            child.getValue(RemoteMeta::class.java)
                        }.firstOrNull()
                    )
                }
        }
    }

    override suspend fun insertRemoteMeta(remoteMeta: RemoteMeta): Int {
        database.child("remoteMeta").also { table ->
            table.child("1").setValue(remoteMeta)
        }
        return 1
    }
}
