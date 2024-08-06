package com.christophprenissl.hygienecompanion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.christophprenissl.hygienecompanion.presentation.ui.theme.HygieneCompanionTheme
import com.christophprenissl.hygienecompanion.presentation.ui.theme.appBackgroundDark
import com.christophprenissl.hygienecompanion.presentation.ui.theme.appStatusBarDark
import com.christophprenissl.hygienecompanion.presentation.ui.theme.appStatusBarLight
import com.christophprenissl.hygienecompanion.presentation.view.main.MainView
import com.christophprenissl.hygienecompanion.presentation.view.main.MainViewViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.compose.koinViewModel

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
                val viewModel = koinViewModel<MainViewViewModel>()
                val state = viewModel.state.collectAsStateWithLifecycle()
                MainView(state = state.value, onEvent = viewModel::onEvent)
            }
        }
    }
}
