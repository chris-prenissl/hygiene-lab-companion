package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterSeriesDto
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.CoveringLetterMapper
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.CoveringLetterSeriesMapper
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo
import com.christophprenissl.hygienecompanion.util.SAMPLING_STATE_FIELD
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoveringLetterSeriesRepoImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val coveringLettersRef: CollectionReference,
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
        val snapshotListener = coveringLetterSeriesRef
            .whereNotEqualTo(SAMPLING_STATE_FIELD, SamplingState.LaboratoryResult.name)
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

    override suspend fun createCoveringLetterSeriesInFireStore(coveringLetterSeries: CoveringLetterSeries) = flow {

        try {
            emit(Response.Loading)
            val coveringLetterSeriesMapper = CoveringLetterSeriesMapper()
            val coveringLetterMapper = CoveringLetterMapper()

            val saved = db.runBatch { batch ->
                val coveringLetterSeriesId = coveringLetterSeriesRef.document().id
                coveringLetterSeries.id = coveringLetterSeriesId
                val coveringLetterSeriesDocument = coveringLetterSeriesRef.document(coveringLetterSeriesId)

                coveringLetterSeries.coveringLetters?.forEach {
                    val coveringLetterId = coveringLettersRef.document().id
                    it.id = coveringLetterId
                    it.seriesId = coveringLetterSeriesId
                    val coveringLetterDto = coveringLetterMapper.toEntity(it)
                    val coveringLetterSaveRef = coveringLettersRef.document(coveringLetterId)
                    batch.set(coveringLetterSaveRef, coveringLetterDto)
                }

                val coveringLetterSeriesDto = coveringLetterSeriesMapper.toEntity(coveringLetterSeries)

                batch.set(coveringLetterSeriesDocument, coveringLetterSeriesDto)
            }.await()

            emit(Response.Success(saved))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
