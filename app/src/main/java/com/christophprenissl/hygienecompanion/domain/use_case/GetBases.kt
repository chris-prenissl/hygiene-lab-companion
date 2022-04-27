package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.repository.BasisRepo

class GetBases(
    private val basisRepo: BasisRepo
) {
    operator fun invoke() = basisRepo.getBasesFromFireStore()
}
