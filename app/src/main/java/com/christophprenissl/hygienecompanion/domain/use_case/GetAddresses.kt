package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.repository.AddressRepo

class GetAddresses(
    private val addressRepo: AddressRepo
) {

    operator fun invoke() = addressRepo.getAddressesFromFireStore()

}
