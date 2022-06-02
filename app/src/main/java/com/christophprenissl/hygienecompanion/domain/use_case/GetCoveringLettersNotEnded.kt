package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.data.repository.CoveringLetterRepo

class GetCoveringLettersNotEnded(
    private val coveringLetterRepo: CoveringLetterRepo
) {
    operator fun invoke() = coveringLetterRepo.getCoveringLettersNotEndedFromDatabase()
}
