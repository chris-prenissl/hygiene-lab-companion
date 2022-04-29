package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.model.entity.Sample
import kotlinx.coroutines.flow.Flow

interface CoveringLetterRepo {

    fun getCoveringLettersOfSeriesFromFireStore(seriesId: String): Flow<Response<List<CoveringLetter>>>

    suspend fun saveCoveringLetterOfSeriesToFireStore(seriesId: String, coveringLetter: CoveringLetter): Flow<Response<Void?>>

    suspend fun saveSampleOfCoveringLetterOfSeriesToFireStore(seriesId: String, coveringLetterId: String): Flow<Response<Void?>>

    suspend fun deleteSampleFromCoveringLettersOfSeriesFromFireStore(seriesId: String, coveringLetterId: String, sample: Sample): Flow<Response<Void?>>

}
