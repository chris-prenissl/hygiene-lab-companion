package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.repository.BasisRepo

class GetBases(
    private val basisRepo: BasisRepo,
) {
    operator fun invoke() = basisRepo.getBasesFromDatabase()
}
