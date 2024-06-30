package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.entity.Address
import com.christophprenissl.hygienecompanion.model.repository.SampleLocationRepo

class GetSampleLocations(
    private val sampleLocationRepo: SampleLocationRepo,
) {
    operator fun invoke(
        fromAddress: Address,
    ) = sampleLocationRepo.getSampleLocationsFromDatabase(fromAddress)
}
