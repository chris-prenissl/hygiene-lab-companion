package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo

class GetCoveringLetterSeriesById(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {
    operator fun invoke(id: String) = coveringLetterSeriesRepo
        .getCoveringLetterSeriesFromDatabase(id = id)
}
