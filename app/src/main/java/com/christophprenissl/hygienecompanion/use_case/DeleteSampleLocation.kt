package com.christophprenissl.hygienecompanion.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation
import com.christophprenissl.hygienecompanion.domain.repository.SampleLocationRepo

class DeleteSampleLocation(
    private val sampleRepo: SampleLocationRepo
) {

    suspend operator fun invoke(sampleLocation: SampleLocation) = sampleRepo.deleteSampleLocationFromDatabase(sampleLocation)

}
