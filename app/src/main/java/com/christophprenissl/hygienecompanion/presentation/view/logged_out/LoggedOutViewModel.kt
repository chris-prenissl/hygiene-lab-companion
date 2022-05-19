package com.christophprenissl.hygienecompanion.presentation.view.logged_out

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.domain.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.view.util.loginAs
import com.christophprenissl.hygienecompanion.util.DataStoreUserType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoggedOutViewModel @Inject constructor(
    private val dataStoreUserType: DataStoreUserType
): ViewModel() {

    private var _userType = mutableStateOf("")
    val userType: State<String> = _userType

    private fun getUserType() {
        viewModelScope.launch {
            dataStoreUserType.getUserType().collect {
                _userType.value = it
            }
        }
    }

    fun login(
        userType: UserType,
        onLogin: (UserType) -> Unit
    ) {
        viewModelScope.launch {
            loginAs(
                userTypeStore = dataStoreUserType,
                userType = userType,
                onLogin = onLogin
            )
        }
    }
}
