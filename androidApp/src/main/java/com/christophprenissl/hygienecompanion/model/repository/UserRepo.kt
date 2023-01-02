package com.christophprenissl.hygienecompanion.model.repository

import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepo {

    fun getUserFromDatabase(id: String): Flow<Response<User>>

    suspend fun saveUserToDatabase(user: User): Flow<Response<Void?>>

    suspend fun deleteUserFromDatabase(user: User): Flow<Response<Void?>>

}
