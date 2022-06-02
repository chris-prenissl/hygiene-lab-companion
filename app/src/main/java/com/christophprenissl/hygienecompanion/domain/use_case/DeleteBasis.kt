package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.entity.Basis
import com.christophprenissl.hygienecompanion.data.repository.BasisRepo

class DeleteBasis(
    private val basisRepo: BasisRepo
) {
    suspend operator fun invoke(basis: Basis) = basisRepo.deleteBasisFromDatabase(basis)
}
