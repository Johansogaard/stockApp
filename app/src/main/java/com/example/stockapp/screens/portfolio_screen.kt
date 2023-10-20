package com.example.stockapp.screens

import AuthenticationManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PortfolioScreen(navController: NavController,authManager: AuthenticationManager) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    )
    {
        Text(text = "Get Started")

    }
}