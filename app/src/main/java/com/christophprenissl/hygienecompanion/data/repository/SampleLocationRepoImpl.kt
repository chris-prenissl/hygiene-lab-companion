package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response.Error
import com.christophprenissl.hygienecompanion.domain.model.Response.Success
import com.christophprenissl.hygienecompanion.domain.model.Response.Loading
import com.christophprenissl.hygienecompanion.domain.model.dto.SampleLocationDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.SampleLocationMapper
import com.christophprenissl.hygienecompanion.domain.repository.SampleLocationRepo
import com.christophprenissl.hygienecompanion.util.ADDRESS_ID
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class SampleLocationRepoImpl @Inject constructor(
    private val sampleLocationsRef: CollectionReference
): SampleLocationRepo {
    override fun getSampleLocationsFromDatabase(fromAddress: Address) = callbackFlow {
        val snapshotListener = sampleLocationsRef.whereEqualTo(ADDRESS_ID, fromAddress.id).addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val sampleLocationsDto = snapshot.toObjects(SampleLocationDto::class.java)
                val mapper = SampleLocationMapper(address = fromAddress)
                val samplesLocation = sampleLocationsDto.map {
                    mapper.fromEntity(it)
                }
                Success(samplesLocation)
            } else {
                Error(e?.message ?: e.toString())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun saveSampleLocationToDatabase(sampleLocation: SampleLocation) = flow {
        try {
            emit(Loading)
            val mapper = SampleLocationMapper(null)
            val sampleLocationDto = mapper.toEntity(sampleLocation)
            val sampleLocationId = sampleLocationsRef.document().id
            sampleLocationDto.id = sampleLocationId
            val saved = sampleLocationsRef.document(sampleLocationId).set(sampleLocationDto).await()
            emit(Success(saved))
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }

    override suspend fun deleteSampleLocationFromDatabase(sampleLocation: SampleLocation) = flow {
        try {
            emit(Loading)
            if (sampleLocation.id != null) {
                val deletion = sampleLocationsRef.document(sampleLocation.id).delete().await()
                emit(Success(deletion))
            } else {
                emit(Error("no sample id provided"))
            }
        } catch (e: Exception) {
            emit(Error(e.message ?: e.toString()))
        }
    }
}
