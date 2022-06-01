package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import kotlinx.coroutines.flow.Flow
import java.util.*

interface CoveringLetterSeriesRepo {

    fun getCoveringLetterSeriesFromDatabase(id: String): Flow<Response<CoveringLetterSeries?>>

    fun getCoveringLetterSeriesFromDatabase(): Flow<Response<List<CoveringLetterSeries>>>

    fun getCoveringLetterSeriesNotEndedFromDatabase(): Flow<Response<List<CoveringLetterSeries>>>

    suspend fun createCoveringLetterSeriesInDatabase(
        coveringLetterSeries: CoveringLetterSeries
    ): Flow<Response<Void?>>

    suspend fun createAdditionalCoveringLettersToSeriesInDatabase(
        coveringLetter: CoveringLetter,
        dates: List<Date>
    ): Flow<Response<Void?>>

}
