package com.christophprenissl.hygienecompanion.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.christophprenissl.hygienecompanion.model.entity.User
import com.christophprenissl.hygienecompanion.model.entity.UserType
import kotlinx.coroutines.flow.*

class DataStoreUser(private val context: Context) {

    companion object {
        private val  Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            USER_PREF_STORE
        )
        val USER_NAME_KEY = stringPreferencesKey(USER_NAME_PREF)
        val HAS_CERTIFICATE_KEY = stringPreferencesKey(HAS_CERTIFICATE_PREF)
        val QM_KEY = stringPreferencesKey(QM_PREF)
        val USER_TYPE_KEY = stringPreferencesKey(USER_TYPE_PREF)
    }

    fun getUserType(): Flow<UserType?> = flow {
        context.dataStore.data.first().let { preferences ->
            preferences[USER_TYPE_KEY]?.let {
                emit(UserType.valueOf(it))
            } ?: emit(null)
        }
    }

    fun getUser(): Flow<User?> = flow {
        context.dataStore.data.first().let { preferences ->
            val name = preferences[USER_NAME_KEY]
            val hasCertificate = preferences[HAS_CERTIFICATE_KEY].toBoolean()
            val qm = preferences[QM_KEY].toBoolean()
            val userTypeString = preferences[USER_TYPE_KEY]
            if (name == null || userTypeString == null) {
                emit(null)
            } else {
                emit(
                    User(
                        name = name,
                        hasCertificate = hasCertificate,
                        isSamplerOfInstitute = qm,
                        userType = UserType.valueOf(userTypeString)
                    )
                )
            }
        }
    }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME_KEY] = user.name
            preferences[HAS_CERTIFICATE_KEY] = user.hasCertificate.toString()
            preferences[QM_KEY] = user.isSamplerOfInstitute.toString()
            preferences[USER_TYPE_KEY] = user.userType.name
        }
    }

    suspend fun removeUser() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
