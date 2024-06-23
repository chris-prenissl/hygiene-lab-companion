package com.christophprenissl.hygienecompanion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import com.christophprenissl.hygienecompanion.presentation.ui.theme.HygieneCompanionTheme
import com.christophprenissl.hygienecompanion.presentation.ui.theme.appBackgroundDark
import com.christophprenissl.hygienecompanion.presentation.ui.theme.appStatusBarDark
import com.christophprenissl.hygienecompanion.presentation.ui.theme.appStatusBarLight
import com.christophprenissl.hygienecompanion.presentation.view.MainView
import com.christophprenissl.hygienecompanion.presentation.view.MainViewViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.java.KoinJavaComponent.get

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (isSystemInDarkTheme()) {
                window.statusBarColor = appStatusBarDark.toArgb()
            } else {
                window.statusBarColor = appStatusBarLight.toArgb()
            }
            window.navigationBarColor = appBackgroundDark.toArgb()
            HygieneCompanionTheme {
               MainView(get(MainViewViewModel::class.java))
           }
        }
    }
}
