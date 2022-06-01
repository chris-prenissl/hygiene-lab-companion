package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.Basis
import kotlinx.coroutines.flow.Flow

interface BasisRepo {

    fun getBasesFromDatabase(): Flow<Response<List<Basis>>>

    suspend fun getBasisByIdFromDatabase(id: String): Flow<Response<Basis>>

    suspend fun saveBasisToDatabase(basis: Basis): Flow<Response<Void?>>

    suspend fun deleteBasisFromDatabase(basis: Basis): Flow<Response<Void?>>

}
