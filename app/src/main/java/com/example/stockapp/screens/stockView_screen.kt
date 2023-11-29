package com.example.stockapp.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen
import com.example.stockapp.ui.theme.TopBarGoBack
import com.example.stockapp.viewModels.StocksViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

enum class IntervalOption(val interval: String, val count: Int) {
    HOUR("MINUTE", 60),
    HOUR12("FIFTEEN_MINUTES", 48),
    HOUR24("FIFTEEN_MINUTES", 96),
    WEEK("HOUR", 24*7),
    MONTH("DAY", 1),
    YEAR("DAY", 7)
}

fun intervalToLabel(intervalOption: IntervalOption): String = when(intervalOption) {
    IntervalOption.HOUR -> "last Hour"
    IntervalOption.HOUR12 -> "last 12 Hour"
    IntervalOption.HOUR24 -> "LAST 24H"
    IntervalOption.WEEK -> "LAST WEEK"
    IntervalOption.MONTH -> "Last Month X"
    IntervalOption.YEAR -> "Last YEAR X"
}
fun findMaxValue(data: List<Triple<Float, Float, Float>>): Float {
    return data.maxOfOrNull { triple ->
        maxOf(triple.first, triple.second, triple.third)
    } ?: Float.MIN_VALUE // Replace with an appropriate default or handle null
}

fun findMinValue(data: List<Triple<Float, Float, Float>>): Float {
    return data.minOfOrNull { triple ->
        minOf(triple.first, triple.second, triple.third)
    } ?: Float.MAX_VALUE // Replace with an appropriate default or handle null
}

@Composable
fun StockViewScreen(navController: NavController, stocksViewModel: StocksViewModel, stockSymbol: String) {
    // Trigger loading stock data based on the symbol
    val coroutineScope = rememberCoroutineScope()
    var stockData by remember { mutableStateOf<List<Triple<Float, Float, Float>>>(emptyList()) }
    var apiError by remember { mutableStateOf<String?>(null) }
    val currentTime = remember { SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date()) }

    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                stockData = getStockData("$stockSymbol", "MINUTE", 60)
                apiError = null
                //you should make these methods public suspend fun getStockData(
                //    ticker: String,
                //    interval: String,
                //    count: Int
                //): List<Triple<Float, Float, Float>> float 1 = avarage foat 2 = min float 3 = max, when using count 150 you get the last 150 of the chosen interval

                //are these next possible?

            } catch (exception: Exception) {
                apiError = exception.localizedMessage // Capture the error message
            }
        }
    }

    fun updateStockData(interval: IntervalOption) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                // Trigger a loading state if needed
                val newData = getStockData("$stockSymbol", interval.interval, interval.count)
                withContext(Dispatchers.Main) {
                    // Update the state in the main thread
                    stockData = newData
                    apiError = null
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    // Handle the error on the main thread
                    apiError = exception.localizedMessage
                }
            }
        }
    }

    Column {
        Column() {
            TopBarGoBack("Details", navController = navController)
        }
        Column(modifier = Modifier
            .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column() {
                Text(text = "$stockSymbol", style = MaterialTheme.typography.titleMedium)
                Text(text = "Index NASDAQ: OMX C25", style = MaterialTheme.typography.titleMedium)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Column() {
                Row() {
                    if (stockData.isNotEmpty()) {
                        Text(
                            text = "USD ${stockData.last().first}",
                            modifier = Modifier.padding(end = 20.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                        // ... other texts
                    } else {
                        Text(
                            text = "Loading...",
                            modifier = Modifier.padding(end = 20.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                    Text(text = "-15,03 (0,91%) today", style = MaterialTheme.typography.bodyLarge)
                }
                Text(
                    text = "$currentTime",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.DarkGray
                )
            }
            Divider(color = Color.LightGray, thickness = 1.dp)

            Box( modifier = Modifier
                .fillMaxWidth() // Fill the entire width
                .fillMaxHeight(0.5f) ){

                if (apiError != null) {
                    Text("API Error: $apiError")
                } else if (stockData.isNotEmpty()) {
                    StockGraph( stockData)
                } else {
                    Text("Loading...")
                }
            }

            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IntervalOption.values().forEach { intervalOption ->
                    Button(
                        onClick = { updateStockData(intervalOption) },
                        modifier = Modifier
                            .padding(horizontal = 4.dp) // Add padding between buttons.
                            .wrapContentWidth() // Allow buttons to wrap content.
                    ) {
                        Text(text = intervalToLabel(intervalOption))
                    }
                }
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Column() {
                Row() {
                    Column(modifier = Modifier.padding(end = 24.dp)) {
                        Text(
                            text = "Opening",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "HIGH",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "LOW",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(modifier = Modifier.padding(end = 36.dp)) {
                        if (stockData.isNotEmpty()) {
                            val high = findMaxValue(stockData).toString()
                            val low = findMinValue(stockData).toString()

                            Text(
                                text = "x",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = " $high",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = " $low",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        } else {
                            Text(
                                text = "Loading...",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }}
                    Column(modifier = Modifier.padding(end = 24.dp)) {
                        Text(
                            text = "Prev. close",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "High in 52W",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "Low in 52W",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column() {
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "X",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier
                    .align(Alignment.CenterStart),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Column() {
                        Text(text = "Price", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "1,789.00", style = MaterialTheme.typography.titleMedium)
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 28.dp)
                            .fillMaxWidth(),
                        onClick = { navController.navigate(Screen.BuyScreen1.route) },) {
                        Text(text = "TRADE", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }

}

@Preview (showBackground = true)
@Composable
fun StockViewScreenPreview() {
    StockViewScreen(navController = rememberNavController(), stocksViewModel = StocksViewModel(), "NOVO")
}