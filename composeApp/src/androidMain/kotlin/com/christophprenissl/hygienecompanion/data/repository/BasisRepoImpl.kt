package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.dto.BasisDto
import com.christophprenissl.hygienecompanion.model.entity.Basis
import com.christophprenissl.hygienecompanion.model.repository.BasisRepo
import com.christophprenissl.hygienecompanion.model.util.mapper.BasisMapper
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasisRepoImpl @Inject constructor(
    private val basisRef: CollectionReference,
) : BasisRepo {

    override fun getBasesFromDatabase() = callbackFlow {
        val snapshotListener = basisRef.addSnapshotListener { snapshot, e ->
            trySend(Response.Loading)
            val response = if (snapshot != null) {
                val basesDto = snapshot.toObjects(BasisDto::class.java)
                val mapper = BasisMapper()
                val bases = basesDto.map {
                    mapper.fromEntity(it)
                }
                Response.Success(bases)
            } else {
                Response.Error(e?.message ?: e.toString())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun getBasisByIdFromDatabase(id: String) = flow {
        try {
            emit(Response.Loading)
            val mapper = BasisMapper()
            val basisDto = basisRef.document(id).get().await().toObject(BasisDto::class.java)
            if (basisDto != null) {
                val basis = mapper.fromEntity(basisDto)
                emit(Response.Success(basis))
            } else {
                emit(Response.Error("basis not found"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun saveBasisToDatabase(basis: Basis) = flow {
        try {
            emit(Response.Loading)
            val mapper = BasisMapper()
            val basisDto = mapper.toEntity(basis)
            if (basisDto.norm != null) {
                val saved = basisRef.document(basisDto.norm).set(basisDto).await()
                emit(Response.Success(saved))
            } else {
                emit(Response.Error("No norm provided"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun deleteBasisFromDatabase(basis: Basis) = flow {
        try {
            emit(Response.Loading)
            val deletion = basisRef.document(basis.norm).delete().await()
            emit(Response.Success(deletion))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
