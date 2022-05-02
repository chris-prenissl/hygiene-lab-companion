package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import kotlinx.coroutines.flow.Flow

interface CoveringLetterSeriesRepo {

    fun getSamplingSeriesFromFireStore(): Flow<Response<List<CoveringLetterSeries>>>

    fun getSamplingSeriesNotEndedFromFireStore(): Flow<Response<List<CoveringLetterSeries>>>

    suspend fun saveSamplingSeriesToFireStore(coveringLetterSeries: CoveringLetterSeries): Flow<Response<Void?>>

}
