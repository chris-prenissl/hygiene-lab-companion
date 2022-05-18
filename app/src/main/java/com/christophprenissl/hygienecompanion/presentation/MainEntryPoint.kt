package com.christophprenissl.hygienecompanion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import com.christophprenissl.hygienecompanion.presentation.ui.theme.HygieneCompanionTheme
import com.christophprenissl.hygienecompanion.presentation.ui.theme.UKRBackgroundDark
import com.christophprenissl.hygienecompanion.presentation.ui.theme.UKRStatusBarDark
import com.christophprenissl.hygienecompanion.presentation.ui.theme.UKRStatusBarLight
import com.christophprenissl.hygienecompanion.presentation.view.MainView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (isSystemInDarkTheme()) {
                window.statusBarColor = UKRStatusBarDark.toArgb()
            } else {
                window.statusBarColor = UKRStatusBarLight.toArgb()
            }
            window.navigationBarColor = UKRBackgroundDark.toArgb()
            HygieneCompanionTheme {
               MainView()
           }
        }
    }
}
