package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.BasisDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.BasisMapper
import com.christophprenissl.hygienecompanion.domain.repository.BasisRepo
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BasisRepoImpl @Inject constructor(
     private val basisRef: CollectionReference
): BasisRepo {

    override fun getBasesFromFireStore() = callbackFlow {
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

    override suspend fun saveBasisToFireStore(basis: Basis) = flow {
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

    override suspend fun deleteBasisFromFireStore(basis: Basis) = flow {
        try {
            emit(Response.Loading)
            if (basis.norm != null) {
                val deletion = basisRef.document(basis.norm).delete().await()
                emit(Response.Success(deletion))
            } else {
                emit(Response.Error("no address id provided"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
