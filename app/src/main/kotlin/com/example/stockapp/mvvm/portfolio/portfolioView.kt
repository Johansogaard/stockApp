package com.example.stockapp.mvvm.portfolio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.integration.firebase.authentication.EmailAuthManager
import androidx.compose.runtime.Composable
import com.example.stockapp.ui.theme.*
import androidx.compose.runtime.*
import com.example.stockapp.mvvm.Screen
import com.example.stockapp.stockApi.getStockData
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers


@Composable
fun PortfolioScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        PortfolioLayout(navController)
    }
}

@Composable
fun PortfolioLayout(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var stockData by remember { mutableStateOf<List<Triple<Float, Float, Float>>>(emptyList()) }
    var apiError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                stockData = getStockData("AAPL", "MINUTE", 150)
                apiError = null // Reset the error message
            } catch (exception: Exception) {
                apiError = exception.localizedMessage // Capture the error message
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    ) {
        Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, // Align children to the start and end of the Row
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = stringResource(R.string.portfolio_overview))
            IconButton(
                onClick = {
                          EmailAuthManager.signOut()
                    navController.navigate(Screen.IntroScreen.route)
                },


            ) {
                Icon(
                    imageVector = Icons.Rounded.ExitToApp,
                    contentDescription = stringResource(R.string.portfolio_exit_desc),
                    tint = Color.Black // You can change the icon color here
                )
            }
        }
        Box( modifier = Modifier
            .fillMaxWidth() // Fill the entire width
            .fillMaxHeight(0.5f) ){
        if (apiError != null) {
            Text("API Error: $apiError")
        } else if (stockData.isNotEmpty()) {
            StockGraph(stockData)
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPortfolioScreen() {
    PortfolioScreen(navController = rememberNavController())
}