package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.data.repository.BasisRepo

class GetBases(
    private val basisRepo: BasisRepo
) {
    operator fun invoke() = basisRepo.getBasesFromDatabase()
}
