package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import kotlinx.coroutines.flow.Flow
import java.util.*

interface CoveringLetterSeriesRepo {

    fun getCoveringLetterSeriesFromFireStore(id: String): Flow<Response<CoveringLetterSeries?>>

    fun getCoveringLetterSeriesFromFireStore(): Flow<Response<List<CoveringLetterSeries>>>

    fun getCoveringLetterSeriesNotEndedFromFireStore(): Flow<Response<List<CoveringLetterSeries>>>

    suspend fun createCoveringLetterSeriesInFireStore(
        coveringLetterSeries: CoveringLetterSeries
    ): Flow<Response<Void?>>

    suspend fun createAdditionalCoveringLettersToSeriesInFireStore(
        coveringLetter: CoveringLetter,
        dates: List<Date>
    ): Flow<Response<Void?>>

}
