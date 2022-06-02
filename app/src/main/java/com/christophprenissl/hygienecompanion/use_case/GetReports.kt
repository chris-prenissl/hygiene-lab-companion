package com.christophprenissl.hygienecompanion.use_case

import com.christophprenissl.hygienecompanion.domain.repository.CoveringLetterRepo

class GetReports(
    private val coveringLetterRepo: CoveringLetterRepo
) {
    operator fun invoke() = coveringLetterRepo.getCoveringLettersWithLabResultFromDatabase()
}
