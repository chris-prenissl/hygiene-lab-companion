package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.AddressDto
import com.christophprenissl.hygienecompanion.domain.model.entity.Address
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.AddressMapper
import com.christophprenissl.hygienecompanion.domain.repository.AddressRepo
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class AddressRepoImpl @Inject constructor(
    private val addressesRef: CollectionReference,
    private val addressesQuery: Query,
): AddressRepo {
    override fun getAddressesFromDatabase() = callbackFlow {
        val snapshotListener = addressesQuery.addSnapshotListener { snapshot, e ->
            val response = if (snapshot != null) {
                val addressesDto = snapshot.toObjects(AddressDto::class.java)
                val mapper = AddressMapper()
                val addresses = addressesDto.map {
                    mapper.fromEntity(it)
                }
                Response.Success(addresses)
            } else {
                Response.Error(e?.message ?: e.toString())
            }
            trySend(response).isSuccess
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun saveAddressToDatabase(address: Address) = flow {
        try {
            emit(Response.Loading)
            val mapper = AddressMapper()
            val addressDto = mapper.toEntity(address)
            val addressId = addressesRef.document().id
            addressDto.id = addressId
            val saved = addressesRef.document(addressId).set(addressDto).await()
            emit(Response.Success(saved))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun deleteAddressFromDatabase(address: Address) = flow {
        try {
            emit(Response.Loading)
            if (address.id != null) {
                val deletion = addressesRef.document(address.id).delete().await()
                emit(Response.Success(deletion))
            } else {
                emit(Response.Error("no address id provided"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
