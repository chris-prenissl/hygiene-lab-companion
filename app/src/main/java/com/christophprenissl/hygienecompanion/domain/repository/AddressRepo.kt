package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepo {
    fun getAddressesFromFireStore(): Flow<Response<List<Address>>>

    suspend fun saveAddressToFireStore(address: Address): Flow<Response<Void?>>

    suspend fun deleteAddressFromFireStore(address: Address): Flow<Response<Void?>>
}
