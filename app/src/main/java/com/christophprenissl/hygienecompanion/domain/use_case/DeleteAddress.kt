package com.christophprenissl.hygienecompanion.domain.use_case


import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.repository.AddressRepo

class DeleteAddress(
    private val addressRepo: AddressRepo
) {
    suspend operator fun invoke(address: Address) = addressRepo.deleteAddressFromDatabase(address)
}
