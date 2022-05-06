package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.presentation.util.Screen
import com.christophprenissl.hygienecompanion.presentation.view.component.CoveringLetterCard
import com.christophprenissl.hygienecompanion.util.standardPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLettersView(
    navController: NavController,
    viewModel: CoveringLettersViewModel
){
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Text("Anstehende Probeentnahmen")
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }

        when(val response = viewModel.gotCoveringLettersState.value) {
            is Response.Success -> {
                val groupedCoveringLetters = response.data
                groupedCoveringLetters.forEach { (initial, coveringLetters) ->
                    initial?.let {
                        stickyHeader {
                            Text(initial)
                        }
                    }
                    items(coveringLetters) {
                        CoveringLetterCard(
                            coveringLetter = it,
                            onClick = {
                                viewModel.chooseCoveringLetter(it)
                                navController.navigate(Screen.CoveringLetterDetail.route)
                            }
                        )
                    }
                }

            }
            else -> {
                item {
                    Text("not loaded")
                }
            }
        }
    }
}
