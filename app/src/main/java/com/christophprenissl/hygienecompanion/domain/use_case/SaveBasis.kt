package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.domain.repository.BasisRepo

class SaveBasis(
    private val basisRepo: BasisRepo
) {
    suspend operator fun invoke(basis: Basis) = basisRepo.saveBasisToDatabase(basis)
}
