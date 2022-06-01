package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterDto
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.CoveringLetterMapper
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterRepo
import com.christophprenissl.hygienecompanion.util.SAMPLING_STATE_FIELD
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoveringLetterRepoImpl @Inject constructor(
    private val coveringLetterRef: CollectionReference
): CoveringLetterRepo {

    override fun getCoveringLettersNotEndedFromDatabase() = callbackFlow {
        val snapshotListener = coveringLetterRef
            .whereNotEqualTo(SAMPLING_STATE_FIELD, SamplingState.LaboratoryResult.name)
            .addSnapshotListener { snapshot, e ->
                trySend(Response.Loading)
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

    override fun getCoveringLettersWithLabResultFromDatabase() = callbackFlow {
        val snapshotListener = coveringLetterRef
            .whereEqualTo(SAMPLING_STATE_FIELD, SamplingState.LaboratoryResult.name)
            .addSnapshotListener { snapshot, e ->
                trySend(Response.Loading)
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

    override suspend fun saveCoveringLetterToDatabase(coveringLetter: CoveringLetter) = flow {
        try {
            emit(Response.Loading)
            val coveringLetterMapper = CoveringLetterMapper()
            val coveringLetterDto = coveringLetterMapper.toEntity(coveringLetter)
            if (coveringLetterDto.id != null) {
                val saved = coveringLetterRef.document(coveringLetterDto.id).set(coveringLetterDto).await()
                emit(Response.Success(saved))
            } else {
                emit(Response.Error("No id provided"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
