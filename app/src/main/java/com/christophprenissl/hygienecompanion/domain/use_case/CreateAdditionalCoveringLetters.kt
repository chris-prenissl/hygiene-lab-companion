package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.data.repository.CoveringLetterSeriesRepo
import java.util.*

class CreateAdditionalCoveringLetters(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {
    suspend operator fun invoke(
        coveringLetter: CoveringLetter,
        dates: List<Date>
    ) = coveringLetterSeriesRepo.createAdditionalCoveringLettersToSeriesInDatabase(
        coveringLetter = coveringLetter,
        dates = dates
    )
}