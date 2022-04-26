package com.christophprenissl.hygienecompanion.domain.use_case

import com.christophprenissl.hygienecompanion.domain.repository.AddressRepo

class GetAddresses(
    private val repository: AddressRepo
) {

    operator fun invoke() = repository.getAddressesFromFireStore()

}