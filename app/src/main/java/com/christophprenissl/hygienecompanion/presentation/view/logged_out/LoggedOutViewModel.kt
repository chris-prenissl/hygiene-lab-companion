package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.model.entity.User
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.view.util.loginAs
import com.christophprenissl.hygienecompanion.util.DataStoreUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoggedOutViewModel @Inject constructor(
    private val dataStoreUser: DataStoreUser
): ViewModel() {

    private var _userNameState = mutableStateOf("")
    val userNameState: State<String> = _userNameState

    private var _hasCertificateState = mutableStateOf(false)
    val hasCertificateState: State<Boolean> = _hasCertificateState

    private var _isUserOfInstituteState = mutableStateOf(false)
    val isUserOfInstituteState: State<Boolean> = _isUserOfInstituteState

    private var _userTypeState = mutableStateOf(UserType.Sampler)
    val userTypeState: State<UserType> = _userTypeState

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            dataStoreUser.getUser().collect { user ->
                user.name.let { _userNameState.value = it }
                user.userType.let { _userTypeState.value = it }
                user.hasCertificate.let { _hasCertificateState.value = it }
                user.isSamplerOfInstitute.let { _isUserOfInstituteState.value = it }
            }
        }
    }

    fun setUserName(name: String) {
        _userNameState.value = name
    }

    fun setUserType(type: UserType) {
        _userTypeState.value = type
    }

    fun setUserHasCertificate(value: Boolean) {
        _hasCertificateState.value = value
    }

    fun setUserIsSamplerOfInstitute(value: Boolean) {
        _isUserOfInstituteState.value = value
    }

    fun login(
        onLogin: (UserType) -> Unit
    ) {
        val user = User(
            name = _userNameState.value,
            hasCertificate = if (_userTypeState.value != UserType.LabWorker) _hasCertificateState.value else false,
            isSamplerOfInstitute = if (_userTypeState.value != UserType.LabWorker) _isUserOfInstituteState.value else false,
            userType = _userTypeState.value
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
