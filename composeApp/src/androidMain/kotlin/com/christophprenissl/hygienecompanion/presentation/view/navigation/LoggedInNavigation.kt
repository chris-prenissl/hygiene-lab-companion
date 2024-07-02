package com.christophprenissl.hygienecompanion.presentation.view.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.NavItemsList
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.BasisDetailView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterSeriesDetailView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.SampleLocationsView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLetterDetailView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLettersView
import com.christophprenissl.hygienecompanion.presentation.view.reports.ReportDetailView
import com.christophprenissl.hygienecompanion.presentation.view.reports.ReportsView
import com.christophprenissl.hygienecompanion.util.doubleStandardPadding
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(
    ExperimentalMaterial3AdaptiveApi::class,
    InternalComposeApi::class,
    ExperimentalCoroutinesApi::class,
)
@Composable
fun LoggedInNavigation(
    barNavItems: List<Screen>,
) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Screen>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }

    Scaffold { padding ->
        Box(modifier = Modifier.padding(padding)) {
            ListDetailPaneScaffold(
                directive = navigator.scaffoldDirective,
                value = navigator.scaffoldValue,
                listPane = {
                    AnimatedPane(
                        modifier = Modifier
                            .padding(doubleStandardPadding.dp)
                            .preferredWidth(250.dp),
                    ) {
                        NavItemsList(
                            screenList = barNavItems,
                            onNavigate = { screen ->
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, screen)
                            },
                        )
                    }
                },
                detailPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let { destination ->
                            when (destination) {
                                Screen.CoveringLetters -> CoveringLettersView(
                                    onNavigate = {
                                        navigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Extra,
                                            it,
                                        )
                                    },
                                )

                                Screen.CoveringLetterBasis -> CoveringLetterBasisView(
                                    onNavigate = {
                                        navigator.navigateTo(
                                            ListDetailPaneScaffoldRole.Extra,
                                            it,
                                        )
                                    },
                                )

                                Screen.Reports -> {
                                    ReportsView(
                                        onNavigate = {
                                            navigator.navigateTo(
                                                ListDetailPaneScaffoldRole.Extra,
                                                it,
                                            )
                                        },
                                    )
                                }

                                else -> Unit
                            }
                        }
                    }
                },
                extraPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let { destination ->
                            when (destination) {
                                Screen.CoveringLetterDetail -> {
                                    CoveringLetterDetailView(onNavigateUp = navigator::navigateBack)
                                }
                                Screen.CoveringLetterSeriesDetail -> {
                                    CoveringLetterSeriesDetailView()
                                }
                                Screen.SampleLocations -> {
                                    SampleLocationsView()
                                }
                                Screen.BasisDetail -> {
                                    BasisDetailView()
                                }
                                Screen.ReportDetail -> {
                                    ReportDetailView()
                                }
                                else -> Unit
                            }
                        }
                    }
                },
            )
        }
    }
}
