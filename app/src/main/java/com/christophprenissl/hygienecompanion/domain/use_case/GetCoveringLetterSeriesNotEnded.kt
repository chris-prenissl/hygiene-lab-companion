package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.data.repository.CoveringLetterSeriesRepo

class GetCoveringLetterSeriesNotEnded(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {
    operator fun invoke() = coveringLetterSeriesRepo.getCoveringLetterSeriesNotEndedFromDatabase()
}
