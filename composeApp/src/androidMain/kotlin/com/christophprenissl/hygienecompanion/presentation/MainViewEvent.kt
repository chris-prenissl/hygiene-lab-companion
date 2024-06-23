package com.christophprenissl.hygienecompanion.presentation

sealed interface MainViewEvent {
    data object Logout : MainViewEvent
}