package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.entity.User
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.view.util.loginAs
import com.christophprenissl.hygienecompanion.util.DataStoreUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoggedOutViewModel @Inject constructor(
    private val dataStoreUser: DataStoreUser
): ViewModel() {

    private var _userType = mutableStateOf<UserType?>(null)
    val userType: State<UserType?> = _userType

    private fun getUserType() {
        viewModelScope.launch {
            dataStoreUser.getUserType().collect {
                _userType.value = it
            }
        }
    }

    fun login(
        name: String,
        hasCertificate: Boolean,
        isSamplerOfInstitute: Boolean,
        userType: UserType,
        onLogin: (UserType) -> Unit
    ) {
        val user = User(
            name = name,
            hasCertificate = if (userType != UserType.LabWorker) hasCertificate else null,
            isSamplerOfInstitute = if (userType != UserType.LabWorker) isSamplerOfInstitute else null,
            userType = userType
        )
        viewModelScope.launch {
            loginAs(
                userDataStore = dataStoreUser,
                user = user,
                onLogin = onLogin
            )
        }
    }
}
