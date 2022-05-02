package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import kotlinx.coroutines.flow.Flow

interface BasisRepo {

    fun getBasesFromFireStore(): Flow<Response<List<Basis>>>

    suspend fun getBasisById(id: String): Flow<Response<Basis>>

    suspend fun saveBasisToFireStore(basis: Basis): Flow<Response<Void?>>

    suspend fun deleteBasisFromFireStore(basis: Basis): Flow<Response<Void?>>

}
