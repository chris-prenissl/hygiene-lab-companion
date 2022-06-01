package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepo {
    fun getAddressesFromDatabase(): Flow<Response<List<Address>>>

    suspend fun saveAddressToDatabase(address: Address): Flow<Response<Void?>>

    suspend fun deleteAddressFromDatabase(address: Address): Flow<Response<Void?>>
}
