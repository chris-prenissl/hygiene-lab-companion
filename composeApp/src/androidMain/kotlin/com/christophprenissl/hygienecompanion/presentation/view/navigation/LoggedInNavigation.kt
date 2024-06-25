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
import androidx.compose.ui.Modifier
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.NavItemsList
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLetterDetailView
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLettersView

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun LoggedInNavigation(
    navItems: List<Screen>
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
                    AnimatedPane {
                        NavItemsList(
                            screenList = navItems,
                            onNavigate = { screen ->
                                navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, screen)
                            }
                        )
                    }
                },
                detailPane = {
                    AnimatedPane {
                        navigator.currentDestination?.content?.let {
                            CoveringLettersView(
                                onNavigateToDetail = {
                                    navigator.navigateTo(ListDetailPaneScaffoldRole.Extra, Screen.CoveringLetterDetail)
                                }
                            )
                        }
                    }
                },
                extraPane = {
                    AnimatedPane {
                        CoveringLetterDetailView(onNavigateUp = navigator::navigateBack)
                    }
                }
            )
        }
    }
}