package com.christophprenissl.hygienecompanion.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DataStoreUserType(private val context: Context) {

    companion object {
        private val  Context.dataStore: DataStore<Preferences> by preferencesDataStore(USER_TYPE)
        val USER_TYPE_KEY = stringPreferencesKey(USER_TYPE)
    }

    val getUserType = flow {
        val userType = context.dataStore.data.map { preferences ->
            preferences[USER_TYPE_KEY] ?: UserType.Sampler.name
        }
        emit(userType)
    }

    suspend fun saveUserType(type: UserType) {
        context.dataStore.edit { preferences ->
            preferences[USER_TYPE_KEY] = type.name
        }
    }
}
