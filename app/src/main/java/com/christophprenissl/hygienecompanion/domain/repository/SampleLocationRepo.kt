package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation
import kotlinx.coroutines.flow.Flow

interface SampleLocationRepo {
    fun getSampleLocationsFromFireStore(): Flow<Response<List<SampleLocation>>>

    suspend fun saveSampleLocationToFireStore(sampleLocation: SampleLocation): Flow<Response<Void?>>

    suspend fun deleteSampleLocationFromFireStore(sampleLocation: SampleLocation): Flow<Response<Void?>>
}