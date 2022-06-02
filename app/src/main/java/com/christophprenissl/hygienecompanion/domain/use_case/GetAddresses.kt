package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.data.repository.AddressRepo

class GetAddresses(
    private val addressRepo: AddressRepo
) {

    operator fun invoke() = addressRepo.getAddressesFromDatabase()

}
