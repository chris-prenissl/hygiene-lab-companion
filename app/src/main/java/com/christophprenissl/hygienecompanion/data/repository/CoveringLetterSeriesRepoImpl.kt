package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterSeriesDto
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.CoveringLetterSeriesMapper
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo
import com.christophprenissl.hygienecompanion.util.HAS_ENDED
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoveringLetterSeriesRepoImpl @Inject constructor(
    private val coveringLetterSeriesRef: CollectionReference
): CoveringLetterSeriesRepo {
    override fun getCoveringLetterSeriesFromFireStore() = callbackFlow {
        val snapshotListener = coveringLetterSeriesRef
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val coveringLetterSeriesDto = snapshot.toObjects(CoveringLetterSeriesDto::class.java)
                    val mapper = CoveringLetterSeriesMapper()
                    val coveringLetterSeries = coveringLetterSeriesDto.map {
                        mapper.fromEntity(it)
                    }
                    Response.Success(coveringLetterSeries)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getCoveringLetterSeriesNotEndedFromFireStore() = callbackFlow {
        val snapshotListener = coveringLetterSeriesRef.whereEqualTo(HAS_ENDED, false)
            .addSnapshotListener { snapshot, e ->
                val response = if (snapshot != null) {
                    val coveringLetterSeriesDto = snapshot.toObjects(CoveringLetterSeriesDto::class.java)
                    val mapper = CoveringLetterSeriesMapper()
                    val coveringLetterSeries = coveringLetterSeriesDto.map {
                        mapper.fromEntity(it)
                    }
                    Response.Success(coveringLetterSeries)
                } else {
                    Response.Error(e?.message ?: e.toString())
                }
                trySend(response).isSuccess
            }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun saveCoveringLetterSeriesToFireStore(coveringLetterSeries: CoveringLetterSeries)= flow {
        try {
            emit(Response.Loading)
            val mapper = CoveringLetterSeriesMapper()
            val coveringLetterSeriesDto = mapper.toEntity(coveringLetterSeries)
            val coveringLetterSeriesId = coveringLetterSeriesRef.document().id
            coveringLetterSeries.id = coveringLetterSeriesId
            val saved = coveringLetterSeriesRef
                .document(coveringLetterSeriesId)
                .set(coveringLetterSeriesDto)
                .await()
            emit(Response.Success(saved))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
