package com.example.stockapp.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.stockapp.ui.theme.Stock

import androidx.compose.material3.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.ui.theme.Accent

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign

import com.example.stockapp.R
import com.example.stockapp.data.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun WatchScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        WatchLayout(navController)
    }
}

@Composable
fun WatchLayout(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var stockNames by remember { mutableStateOf<List<String>>(emptyList()) }
    var stockPrices by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var stockStarts by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var isLoading by remember { mutableStateOf(true) } // To handle loading state

    LaunchedEffect(key1 = Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
             stockNames = listOf("NOVO","NOVO","OIL","OIL","APPL","APPL")
                // Update this as per your logic

                val tempStockPrices = mutableMapOf<String, String>()
                val tempStockStarts = mutableMapOf<String, String>()
                stockNames.forEach { stockName ->
                    val priceString = getStockPrice(stockName, "MINUTE")
                    val startString = getStockPrice(stockName, "DAY")
                    val price = priceString.toDoubleOrNull() ?: 0.0
                    val start = startString.toDoubleOrNull() ?: 0.0
                    val change = price - start

                    tempStockPrices[stockName] = priceString
                    tempStockStarts[stockName] = "%.2f".format(change)
                }

                withContext(Dispatchers.Main) {
                    stockPrices = tempStockPrices
                    stockStarts = tempStockStarts
                    isLoading = false
                }
            } catch (e: Exception) {
                // Handle exceptions
                isLoading = false
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator() // Show loading indicator
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Watchlist",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight(800)
                    )
                )
                Divider(
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )

                stockNames.forEach { stockName ->
                    val price = stockPrices[stockName] ?: "Fetching..."
                    val change = stockStarts[stockName] ?: "Fetching..."
                    Stock(
                        picture = activeButtonPicture, // Make sure this is correctly managed
                        text = stockName,
                        price = price,
                        perftdy = change,
                        navController
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun WatchScreenPreview() {
    val navController = rememberNavController()
    WatchScreen(navController)
}
