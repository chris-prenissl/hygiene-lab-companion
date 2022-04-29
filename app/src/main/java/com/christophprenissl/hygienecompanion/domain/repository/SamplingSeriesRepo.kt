package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.SamplingSeries
import kotlinx.coroutines.flow.Flow

interface SamplingSeriesRepo {

    fun getSamplingSeriesFromFireStore(): Flow<Response<List<SamplingSeries>>>

    fun getSamplingSeriesNotEndedFromFireStore(): Flow<Response<List<SamplingSeries>>>

    suspend fun saveSamplingSeriesToFireStore(samplingSeries: SamplingSeries): Flow<Response<Void?>>

}
