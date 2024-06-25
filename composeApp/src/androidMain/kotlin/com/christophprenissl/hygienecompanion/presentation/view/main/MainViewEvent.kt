package com.christophprenissl.hygienecompanion.presentation.view.main

sealed interface MainViewEvent {
    data object Logout : MainViewEvent
}