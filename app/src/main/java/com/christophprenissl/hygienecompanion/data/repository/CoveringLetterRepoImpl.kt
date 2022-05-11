package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.CoveringLetterDto
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingState
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.CoveringLetterMapper
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterRepo
import com.christophprenissl.hygienecompanion.util.SAMPLING_STATE_FIELD
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoveringLetterRepoImpl @Inject constructor(
    private val coveringLetterRef: CollectionReference
): CoveringLetterRepo {

    override fun getCoveringLettersNotEndedFromFireStore() = callbackFlow {
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
}
