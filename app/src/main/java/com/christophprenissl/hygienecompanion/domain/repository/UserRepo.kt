package com.christophprenissl.hygienecompanion.domain.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {

    fun getUserFromFireStore(id: String?): Flow<Response<User>>

    suspend fun saveUserToFireStore(user: User): Flow<Response<Void?>>

    suspend fun deleteUserFromFireStore(user: User): Flow<Response<Void?>>

}
