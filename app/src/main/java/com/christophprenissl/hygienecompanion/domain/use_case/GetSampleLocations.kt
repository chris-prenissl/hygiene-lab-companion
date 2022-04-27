package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.repository.SampleLocationRepo

class GetSampleLocations(
    private val repository: SampleLocationRepo
) {

    operator fun invoke(fromAddress: Address) = repository.getSampleLocationsFromFireStore(fromAddress)

}
