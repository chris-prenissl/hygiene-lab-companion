package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import kotlinx.coroutines.flow.Flow

interface CoveringLetterRepo {
    fun getCoveringLettersNotEndedFromDatabase(): Flow<Response<List<CoveringLetter>>>

    fun getCoveringLettersWithLabResultFromDatabase(): Flow<Response<List<CoveringLetter>>>

    suspend fun saveCoveringLetterToDatabase(coveringLetter: CoveringLetter): Flow<Response<Void?>>
}
