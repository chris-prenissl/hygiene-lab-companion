package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import kotlinx.coroutines.flow.Flow

interface CoveringLetterSeriesRepo {

    fun getCoveringLetterSeriesFromFireStore(): Flow<Response<List<CoveringLetterSeries>>>

    fun getCoveringLetterSeriesNotEndedFromFireStore(): Flow<Response<List<CoveringLetterSeries>>>

    suspend fun saveCoveringLetterSeriesToFireStore(coveringLetterSeries: CoveringLetterSeries): Flow<Response<Void?>>

}
