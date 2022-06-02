package com.christophprenissl.hygienecompanion.use_case

import com.christophprenissl.hygienecompanion.domain.repository.AddressRepo

class GetAddresses(
    private val addressRepo: AddressRepo
) {

    operator fun invoke() = addressRepo.getAddressesFromDatabase()

}
