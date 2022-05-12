package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import kotlinx.coroutines.flow.Flow

interface CoveringLetterRepo {
    fun getCoveringLettersNotEndedFromFireStore(): Flow<Response<List<CoveringLetter>>>

    fun getCoveringLettersWithLabResultFromFireStore(): Flow<Response<List<CoveringLetter>>>

    suspend fun saveCoveringLetterToFireStore(coveringLetter: CoveringLetter): Flow<Response<Void?>>
}
