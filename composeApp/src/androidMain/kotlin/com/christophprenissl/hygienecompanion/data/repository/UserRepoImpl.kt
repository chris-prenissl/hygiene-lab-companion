package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.model.dto.UserDto
import com.christophprenissl.hygienecompanion.model.entity.User
import com.christophprenissl.hygienecompanion.model.repository.UserRepo
import com.christophprenissl.hygienecompanion.model.util.mapper.UserMapper
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepoImpl @Inject constructor(
    private val userRef: CollectionReference
): UserRepo {
    override fun getUserFromDatabase(id: String) = flow {
        try {
            emit(Response.Loading)
            val mapper = UserMapper()
            val userDto = userRef.document(id).get().await().toObject(UserDto::class.java)
            if (userDto != null) {
                val user = mapper.fromEntity(userDto)
                emit(Response.Success(user))
            } else {
                emit(Response.Error("No user found"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun saveUserToDatabase(user: User) = flow {
        try {
            emit(Response.Loading)
            val mapper = UserMapper()
            val userDto = mapper.toEntity(user)
            if (userDto.id != null) {
                val saved = userRef.document(userDto.id).set(userDto).await()
                emit(Response.Success(saved))
            } else {
                emit(Response.Error("User has no id for reference"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

    override suspend fun deleteUserFromDatabase(user: User) = flow {
        try {
            emit(Response.Loading)
            val deletion = userRef.document(user.id).delete().await()
            emit(Response.Success(deletion))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }
}
