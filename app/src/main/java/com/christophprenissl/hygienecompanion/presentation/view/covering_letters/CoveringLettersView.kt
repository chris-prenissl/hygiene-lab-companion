package com.christophprenissl.hygienecompanion.presentation.view.covering_letters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.christophprenissl.hygienecompanion.domain.model.Response
import com.christophprenissl.hygienecompanion.presentation.util.dayMonthYearString
import com.christophprenissl.hygienecompanion.util.standardPadding

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoveringLettersView(
    navController: NavController,
    viewModel: CoveringLettersViewModel = hiltViewModel()
){
    LazyColumn {

        item {
            Text("Anstehende Probeentnahmen")
            Spacer(modifier = Modifier.padding(vertical = standardPadding))
        }


        when(val response = viewModel.gotCoveringLetterSeriesState.value) {
            is Response.Success -> {
                response.data.forEach { cls ->
                    stickyHeader {
                        cls.description?.let { Text(it) }
                    }

                    cls.coveringLetters?.let { cl ->
                        items(cl) { item ->
                            item.date?.let { Text(it.dayMonthYearString()) }
                            Spacer(modifier = Modifier.padding(vertical = standardPadding))
                        }
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
