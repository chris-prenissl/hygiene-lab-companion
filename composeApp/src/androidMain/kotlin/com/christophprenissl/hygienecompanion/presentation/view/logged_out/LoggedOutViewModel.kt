package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.model.entity.User
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.util.DataStoreUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoggedOutViewModel @Inject constructor(
    private val dataStoreUser: DataStoreUser
) : ViewModel() {

    private var _state = MutableStateFlow(LoggedOutState())
    val state: StateFlow<LoggedOutState> = _state

    init {
        getUser()
    }

    private fun getUser() {
        viewModelScope.launch {
            dataStoreUser.getUser().collect { user ->
                if (user == null) {
                    _state.update { LoggedOutState() }
                } else {
                    _state.update {
                        it.copy(
                            name = user.name,
                            hasCertificate = user.hasCertificate,
                            isUserOfInstitute = user.isSamplerOfInstitute,
                            userType = user.userType
                        )
                    }
                }
            }
        }
    }

    fun onEvent(event: LoggedOutEvent) {
        when (event) {
            is LoggedOutEvent.UserNameChanged -> _state.update { it.copy(name = event.name) }
            is LoggedOutEvent.Login -> login()
            is LoggedOutEvent.SetCertificateChanged -> _state.update { it.copy(hasCertificate = event.hasCertificate) }
            is LoggedOutEvent.SetSamplerOfInstituteChanged -> _state.update {
                it.copy(
                    isUserOfInstitute = event.isSamplerOfInstitute
                )
            }

            is LoggedOutEvent.UserTypeChanged -> _state.update { it.copy(userType = event.type) }
        }
    }

    private fun login() {
        val userType = _state.value.userType
        val isNotLabWorker = userType != UserType.LabWorker
        _state.value.name?.let {
            val user = User(
                name = it,
                hasCertificate = if (isNotLabWorker) _state.value.hasCertificate else false,
                isSamplerOfInstitute = if (isNotLabWorker) _state.value.isUserOfInstitute else false,
                userType = userType ?: UserType.Sampler
            )
            viewModelScope.launch {
                dataStoreUser.saveUser(user)
            }
        }
    }
}
