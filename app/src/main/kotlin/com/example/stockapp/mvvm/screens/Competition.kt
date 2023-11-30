package com.example.stockapp.mvvm.screens

import androidx.compose.runtime.Composable
import com.example.stockapp.mvvm.viewModels.CompetitionViewModel

@Composable
fun CompetitionScreen(competitionViewModel: CompetitionViewModel) {
    val state = competitionViewModel.state.value
}