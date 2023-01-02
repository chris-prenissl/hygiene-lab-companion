package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.entity.CoveringLetterSeries
import com.christophprenissl.hygienecompanion.model.repository.CoveringLetterSeriesRepo

class CreateCoveringLetterSeries(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {
    suspend operator fun invoke(
        coveringLetterSeries: CoveringLetterSeries
    ) = coveringLetterSeriesRepo.createCoveringLetterSeriesInDatabase(coveringLetterSeries)
}
