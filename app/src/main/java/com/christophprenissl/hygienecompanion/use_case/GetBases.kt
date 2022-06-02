package com.christophprenissl.hygienecompanion.use_case

import com.christophprenissl.hygienecompanion.domain.repository.BasisRepo

class GetBases(
    private val basisRepo: BasisRepo
) {
    operator fun invoke() = basisRepo.getBasesFromDatabase()
}
