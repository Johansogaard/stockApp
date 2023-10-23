package com.example.stockapp.screens

import androidx.compose.runtime.Composable
import com.example.stockapp.viewModels.CompetitionViewModel

@Composable
fun CompetitionScreen(competitionViewModel: CompetitionViewModel) {
    val state = competitionViewModel.state.value
}