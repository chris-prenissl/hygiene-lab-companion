package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo

class SaveCoveringLetterSeries(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {

    suspend operator fun invoke(coveringLetterSeries: CoveringLetterSeries) = coveringLetterSeriesRepo
        .saveCoveringLetterSeriesToFireStore(coveringLetterSeries)

}