package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.repository.CoveringLetterRepo

class GetReports(
    private val coveringLetterRepo: CoveringLetterRepo
) {
    operator fun invoke() = coveringLetterRepo.getCoveringLettersWithLabResultFromDatabase()
}
