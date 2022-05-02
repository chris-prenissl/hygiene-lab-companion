package com.christophprenissl.hygienecompanion.data.repository

import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.domain.model.dto.UserDto
import com.christophprenissl.hygienecompanion.domain.model.entity.User
import com.christophprenissl.hygienecompanion.domain.model.util.mapper.UserMapper
import com.christophprenissl.hygienecompanion.domain.repository.UserRepo
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class UserRepoImpl @Inject constructor(
    private val userRef: CollectionReference
): UserRepo  {
    override fun getUserFromFireStore(id: String) = flow {
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

    override suspend fun saveUserToFireStore(user: User) = flow {
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

    override suspend fun deleteUserFromFireStore(user: User) = flow {
        try {
            emit(Response.Loading)
            if (user.id != null) {
                val deletion = userRef.document(user.id).delete().await()
                emit(Response.Success(deletion))
            } else {
                emit(Response.Error("no id provided as reference"))
            }
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: e.toString()))
        }
    }

}