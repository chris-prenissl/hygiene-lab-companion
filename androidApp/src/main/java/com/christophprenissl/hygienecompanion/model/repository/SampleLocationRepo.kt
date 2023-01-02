package com.christophprenissl.hygienecompanion.model.repository

import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.entity.Address
import com.christophprenissl.hygienecompanion.model.entity.SampleLocation
import kotlinx.coroutines.flow.Flow

interface SampleLocationRepo {
    fun getSampleLocationsFromDatabase(fromAddress: Address): Flow<Response<List<SampleLocation>>>

    suspend fun saveSampleLocationToDatabase(sampleLocation: SampleLocation): Flow<Response<Void?>>

    suspend fun deleteSampleLocationFromDatabase(sampleLocation: SampleLocation): Flow<Response<Void?>>
}
