package com.christophprenissl.hygienecompanion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLettersView
import com.christophprenissl.hygienecompanion.presentation.view.lab_works.LabWorksView
import com.christophprenissl.hygienecompanion.presentation.view.profile.ProfileView
import com.christophprenissl.hygienecompanion.presentation.view.reports.ReportsView
import com.christophprenissl.hygienecompanion.presentation.ui.theme.HygieneCompanionTheme
import com.christophprenissl.hygienecompanion.presentation.view.component.BottomNavBar
import com.christophprenissl.hygienecompanion.presentation.view.component.TopMenuBar
import com.christophprenissl.hygienecompanion.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HygieneCompanionTheme {
                val navController = rememberNavController()
                val navItems = listOf(
                    Screen.CoveringLetters,
                    Screen.CoveringLetterBasis,
                    Screen.LabWorks,
                    Screen.Reports
                )
                Scaffold(
                    topBar = {
                             TopMenuBar(
                                 navController = navController
                             )
                    },
                    bottomBar = {
                            BottomNavBar(
                                navController = navController,
                                navItems = navItems
                            )
                        }
                ) {
                        NavHost(
                            navController = navController,
                            startDestination = Screen.CoveringLetters.route
                        ) {
                            composable(Screen.CoveringLetters.route) {
                                CoveringLettersView()
                            }
                            composable(Screen.CoveringLetterBasis.route) {
                                CoveringLetterBasisView()
                            }
                            composable(Screen.LabWorks.route) {
                                LabWorksView()
                            }
                            composable(Screen.Reports.route) {
                                ReportsView()
                            }
                            composable(Screen.Profile.route) {
                                ProfileView()
                            }
                        }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HygieneCompanionTheme {
        LabWorksView()
    }
}