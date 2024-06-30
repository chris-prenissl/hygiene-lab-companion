package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.christophprenissl.hygienecompanion.model.Response
import com.christophprenissl.hygienecompanion.presentation.view.component.card.CoveringLetterCard
import com.christophprenissl.hygienecompanion.presentation.view.util.isUserAllowedToEnter
import com.christophprenissl.hygienecompanion.util.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLettersView(
    onNavigateToDetail: () -> Unit,
    viewModel: CoveringLettersViewModel = koinViewModel(),
) {
    val userType = viewModel.userTypeFlow.collectAsState(initial = null)

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(NEXT_COVERING_LETTERS)
            Spacer(modifier = Modifier.padding(vertical = standardPadding.dp))
        }
        when (val response = viewModel.gotCoveringLettersState.value) {
            is Response.Success -> {
                val groupedCoveringLetters = response.data
                groupedCoveringLetters.forEach { (initial, coveringLetters) ->
                    initial?.let {
                        stickyHeader {
                            Text(initial)
                        }
                    }
                    items(coveringLetters, key = { cl -> cl.id }) {
                        if (userType.value != null) {
                            CoveringLetterCard(
                                coveringLetter = it,
                                onClick = {
                                    if (isUserAllowedToEnter(
                                            userType = userType.value,
                                            samplingState = it.samplingState,
                                        )
                                    ) {
                                        viewModel.chooseCoveringLetter(it)
                                        if (viewModel.chosenCoveringLetter.value != null) {
                                            onNavigateToDetail()
                                        }
                                    }
                                },
                                accessIndicator = isUserAllowedToEnter(
                                    userType = userType.value,
                                    samplingState = it.samplingState,
                                ),
                            )
                        }
                    }
                }
            }
            else -> {
                item {
                    Text(NOT_LOADED)
                }
            }
        }
    }
}
