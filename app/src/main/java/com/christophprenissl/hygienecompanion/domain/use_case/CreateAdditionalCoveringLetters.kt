package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterSeriesRepo
import java.util.*

class CreateAdditionalCoveringLetters(
    private val coveringLetterSeriesRepo: CoveringLetterSeriesRepo
) {
    suspend operator fun invoke(
        coveringLetter: CoveringLetter,
        dates: List<Date>
    ) = coveringLetterSeriesRepo.createAdditionalCoveringLettersToSeriesInFireStore(
        coveringLetter = coveringLetter,
        dates = dates
    )
}