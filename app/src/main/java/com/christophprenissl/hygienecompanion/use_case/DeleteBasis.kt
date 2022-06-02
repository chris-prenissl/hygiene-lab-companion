package com.christophprenissl.hygienecompanion.use_case

import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import com.christophprenissl.hygienecompanion.domain.repository.BasisRepo

class DeleteBasis(
    private val basisRepo: BasisRepo
) {
    suspend operator fun invoke(basis: Basis) = basisRepo.deleteBasisFromDatabase(basis)
}
