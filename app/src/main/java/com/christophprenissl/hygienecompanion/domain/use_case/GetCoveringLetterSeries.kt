package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo

class GetCoveringLetterSeries(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {
    operator fun invoke() = coveringLetterSeriesRepo.getCoveringLetterSeriesFromDatabase()
}
