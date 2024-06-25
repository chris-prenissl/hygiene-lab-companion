package com.christophprenissl.hygienecompanion.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.MainViewEvent
import com.christophprenissl.hygienecompanion.presentation.ui.theme.appPrimaryDark
import com.christophprenissl.hygienecompanion.presentation.ui.theme.hygieneWorkerColor
import com.christophprenissl.hygienecompanion.presentation.ui.theme.labWorkerColor
import com.christophprenissl.hygienecompanion.presentation.ui.theme.samplerColor
import com.christophprenissl.hygienecompanion.presentation.util.Route
import com.christophprenissl.hygienecompanion.util.DataStoreUser
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewViewModel @Inject constructor(private val dataStoreUser: DataStoreUser) : ViewModel() {

    private var firstLoadFinished = false
    private var initiallyLoggedIn = false
    val state = dataStoreUser
        .getUser()
        .map {
            val userName = it?.name
            val userType = it?.userType
            if (!firstLoadFinished && userType != null) {
                initiallyLoggedIn = true
                firstLoadFinished = true
            }
            MainViewState(
                userName = userName,
                userType = userType,
                userColor = when (userType) {
                    UserType.Sampler -> samplerColor
                    UserType.LabWorker -> labWorkerColor
                    UserType.HygieneWorker -> hygieneWorkerColor
                    else -> appPrimaryDark
                },
                bottomNavItems = when (userType) {
                    UserType.Sampler -> listOf(Route.CoveringLetters)
                    UserType.LabWorker -> listOf(Route.CoveringLetters)
                    UserType.HygieneWorker -> listOf(
                        Route.CoveringLetters,
                        Route.CoveringLetterBasis,
                        Route.Reports
                    )
                    else -> emptyList()
                },
                initiallyLoggedIn = initiallyLoggedIn
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun onEvent(event: MainViewEvent) {
        when (event) {
            is MainViewEvent.Logout -> {
                viewModelScope.launch {
                    dataStoreUser.removeUser()
                }
            }
        }
    }
}