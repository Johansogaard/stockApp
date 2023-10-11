package com.example.stockapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PortfolioScreen(name: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    )
    {
        Text(text = "Get Started $name")

    }
}