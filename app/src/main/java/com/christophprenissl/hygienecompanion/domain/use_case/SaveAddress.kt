package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.model.entity.Address
import com.christophprenissl.hygienecompanion.data.repository.AddressRepo

class SaveAddress(
    private val addressRepo: AddressRepo
) {
    suspend operator fun invoke(address: Address) = addressRepo.saveAddressToDatabase(address)
}
