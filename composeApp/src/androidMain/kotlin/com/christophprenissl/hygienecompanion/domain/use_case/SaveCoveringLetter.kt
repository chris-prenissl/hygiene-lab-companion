package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.entity.CoveringLetter
import com.christophprenissl.hygienecompanion.model.repository.CoveringLetterRepo

class SaveCoveringLetter(
    private val coveringLetterRepo: CoveringLetterRepo,
) {
    suspend operator fun invoke(
        coveringLetter: CoveringLetter,
    ) = coveringLetterRepo.saveCoveringLetterToDatabase(coveringLetter)
}
