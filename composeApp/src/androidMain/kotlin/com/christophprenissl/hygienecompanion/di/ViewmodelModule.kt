package com.christophprenissl.hygienecompanion.di

import com.christophprenissl.hygienecompanion.presentation.view.covering_letter_basis.CoveringLetterBasisViewModel
import com.christophprenissl.hygienecompanion.presentation.view.covering_letters.CoveringLettersViewModel
import com.christophprenissl.hygienecompanion.presentation.view.logged_out.LoggedOutViewModel
import com.christophprenissl.hygienecompanion.presentation.view.reports.ReportsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { CoveringLetterBasisViewModel(get()) }

    viewModel { CoveringLettersViewModel(get(), get()) }

    viewModel { ReportsViewModel(get()) }

    viewModel { LoggedOutViewModel(get()) }
}