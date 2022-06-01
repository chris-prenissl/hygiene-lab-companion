package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.SampleLocation
import com.christophprenissl.hygienecompanion.domain.repository.SampleLocationRepo

class SaveSampleLocation(private val sampleLocationRepo: SampleLocationRepo
) {
    suspend operator fun invoke(
        sampleLocation: SampleLocation
    ) = sampleLocationRepo.saveSampleLocationToDatabase(sampleLocation)
}
