package com.christophprenissl.hygienecompanion.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import kotlinx.coroutines.flow.*

class DataStoreUserType(private val context: Context) {

    companion object {
        private val  Context.dataStore: DataStore<Preferences> by preferencesDataStore(USER_TYPE)
        val USER_TYPE_KEY = stringPreferencesKey(USER_TYPE)
    }

    fun getUserType(): Flow<UserType> = flow {
        context.dataStore.data.first().let { preferences ->
            val type = preferences[USER_TYPE_KEY] ?: UserType.Sampler.name
            emit(UserType.valueOf(type))
        }
    }

    suspend fun saveUserType(type: UserType) {
        context.dataStore.edit { preferences ->
            preferences[USER_TYPE_KEY] = type.name
        }
    }
}
