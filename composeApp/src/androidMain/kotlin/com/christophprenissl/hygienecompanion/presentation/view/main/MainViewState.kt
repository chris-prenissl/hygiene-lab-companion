package com.christophprenissl.hygienecompanion.presentation.view.main

import androidx.compose.ui.graphics.Color
import com.christophprenissl.hygienecompanion.model.entity.UserType
import com.christophprenissl.hygienecompanion.presentation.util.Screen

data class MainViewState(
    val userName: String? = null,
    val userType: UserType? = null,
    val userColor: Color = Color.White,
    val mainNavItems: List<Screen> = emptyList(),
    val initiallyLoggedIn: Boolean = false,
    val isLoading: Boolean = true,
)
