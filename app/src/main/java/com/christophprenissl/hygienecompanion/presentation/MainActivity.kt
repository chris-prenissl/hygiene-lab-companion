package com.christophprenissl.hygienecompanion.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.christophprenissl.hygienecompanion.presentation.covering_letter_basis.CoveringLetterBasisView
import com.christophprenissl.hygienecompanion.presentation.covering_letters.CoveringLettersView
import com.christophprenissl.hygienecompanion.presentation.lab_works.LabWorksView
import com.christophprenissl.hygienecompanion.presentation.profile.ProfileView
import com.christophprenissl.hygienecompanion.presentation.reports.ReportsView
import com.christophprenissl.hygienecompanion.presentation.ui.theme.HygieneCompanionTheme
import com.christophprenissl.hygienecompanion.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HygieneCompanionTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Routes.COVERING_LETTERS
                ) {
                    composable(Routes.COVERING_LETTERS) {
                        CoveringLettersView()
                    }
                    composable(Routes.COVERING_LETTER_BASIS) {
                        CoveringLetterBasisView()
                    }
                    composable(Routes.LAB_WORKS) {
                        LabWorksView()
                    }
                    composable(Routes.REPORTS) {
                        ReportsView()
                    }
                    composable(Routes.PROFILE) {
                        ProfileView()
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