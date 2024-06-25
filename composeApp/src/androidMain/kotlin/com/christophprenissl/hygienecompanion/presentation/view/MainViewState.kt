package com.christophprenissl.hygienecompanion.presentation.view

import androidx.compose.ui.graphics.Color
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.util.Route

data class MainViewState(
    val userName: String? = null,
    val userType: UserType? = null,
    val userColor: Color = Color.White,
    val bottomNavItems: List<Route> = emptyList(),
    val initiallyLoggedIn: Boolean = false
)
