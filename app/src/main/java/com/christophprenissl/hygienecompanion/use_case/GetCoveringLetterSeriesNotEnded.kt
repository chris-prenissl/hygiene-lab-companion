package com.christophprenissl.hygienecompanion.use_case

import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo

class GetCoveringLetterSeriesNotEnded(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {
    operator fun invoke() = coveringLetterSeriesRepo.getCoveringLetterSeriesNotEndedFromDatabase()
}
