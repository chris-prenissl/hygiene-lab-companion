package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.entity.SampleLocation
import com.christophprenissl.hygienecompanion.model.repository.SampleLocationRepo

class DeleteSampleLocation(
    private val sampleRepo: SampleLocationRepo,
) {

    suspend operator fun invoke(sampleLocation: SampleLocation) = sampleRepo.deleteSampleLocationFromDatabase(
        sampleLocation,
    )
}
