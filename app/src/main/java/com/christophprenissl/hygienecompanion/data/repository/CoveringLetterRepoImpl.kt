package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterDto
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.CoveringLetterMapper
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.SampleMapper
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterRepo
import com.christophprenissl.hygienecompanion.util.COVERING_LETTER_COLLECTION
import com.christophprenissl.hygienecompanion.util.SAMPLE_COLLECTION
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoveringLetterRepoImpl @Inject constructor(
    private val coveringLetterSeriesRef: CollectionReference
): CoveringLetterRepo {
    override suspend fun getCoveringLettersOfSeriesToFireStore(seriesId: String) = callbackFlow {
        val snapshotListener = coveringLetterSeriesRef
            .document(seriesId)
            .collection(COVERING_LETTER_COLLECTION)
            .addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val coveringLettersDto = snapshot.toObjects(CoveringLetterDto::class.java)
                val mapper = CoveringLetterMapper()
                val coveringLetters = coveringLettersDto.map {
                    mapper.fromEntity(it)
                }
                Response.Success(coveringLetters)
            } else {
                Response.Error(e?.message ?: e.toString())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun saveCoveringLetterOfSeriesToFireStore(
        seriesId: String,
        coveringLetter: CoveringLetter
    ) = flow {
        try {
            emit(Response.Loading)
            val mapper = CoveringLetterMapper()
            val coveringLetterDto = mapper.toEntity(coveringLetter)
            if (coveringLetter.id != null) {
                val saved = coveringLetterSeriesRef
                    .document(seriesId)
                    .collection(COVERING_LETTER_COLLECTION)
                    .document(coveringLetter.id)
                    .set(coveringLetterDto)
                    .await()
                emit(Response.Success(saved))
            } else {
                emit(Response.Error("No id provided"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun saveSampleOfCoveringLetterOfSeriesToFireStore(
        seriesId: String,
        coveringLetterId: String,
        sample: Sample
    )= flow {
        try {
            emit(Response.Loading)
            val mapper = SampleMapper()
            val sampleDto = mapper.toEntity(sample)
            if (sampleDto.id != null) {
                val saved = coveringLetterSeriesRef.document(seriesId)
                    .collection(COVERING_LETTER_COLLECTION)
                    .document(coveringLetterId)
                    .collection(SAMPLE_COLLECTION)
                    .document(sampleDto.id)
                    .set(sampleDto)
                    .await()
                emit(Response.Success(saved))
            } else {
                emit(Response.Error("No id provided"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

}
