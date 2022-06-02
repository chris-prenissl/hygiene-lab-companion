package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.entity.Basis
import kotlinx.coroutines.flow.Flow

interface BasisRepo {

    fun getBasesFromDatabase(): Flow<Response<List<Basis>>>

    suspend fun getBasisByIdFromDatabase(id: String): Flow<Response<Basis>>

    suspend fun saveBasisToDatabase(basis: Basis): Flow<Response<Void?>>

    suspend fun deleteBasisFromDatabase(basis: Basis): Flow<Response<Void?>>

}
