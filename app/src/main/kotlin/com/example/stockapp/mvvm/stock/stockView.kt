package com.example.stockapp.mvvm.stock

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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.integration.stockapi.StockApi
import com.example.stockapp.mvvm.Screen
import com.example.stockapp.repositories.stock.StockRepository
import com.example.stockapp.stockApi.findMaxValue
import com.example.stockapp.stockApi.findMinValue
import com.example.stockapp.stockApi.getStockData
import com.example.stockapp.stockApi.getcurrentvalue
import com.example.stockapp.stockApi.getytd
import com.example.stockapp.ui.theme.TopBarGoBack
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.stockapp.ui.theme.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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






@Composable
fun StockViewScreen(navController: NavController, stockViewModel: StockViewModel, stockSymbol: String) {
    var activeButton by remember { mutableStateOf(IntervalOption.HOUR) } // Default active button is HOUR

    val coroutineScope = rememberCoroutineScope()
    var stockData by remember { mutableStateOf<List<Triple<Float, Float, Float>>>(emptyList()) }
    var apiError by remember { mutableStateOf<String?>(null) }
    val currentTime = remember { SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date()) }
    var yearStockData by remember { mutableStateOf<List<Triple<Float, Float, Float>>>(emptyList()) }
    LaunchedEffect(Unit) {
        coroutineScope.launch(Dispatchers.IO) {
            try {
                stockData = getStockData("$stockSymbol", "MINUTE", 60)
                apiError = null
                val currentDate = LocalDate.now()
                val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
                val dateAsInt = currentDate.format(formatter).toInt()
                yearStockData = getStockData("$stockSymbol", "DAY", dateAsInt)
                println(yearStockData)
            } catch (exception: Exception) {
                apiError = exception.localizedMessage
            }
        }
    }

    fun updateStockData(interval: IntervalOption) {
        activeButton = interval
        coroutineScope.launch(Dispatchers.IO) {
            try {
                val newData = getStockData("$stockSymbol", interval.interval, interval.count)
                withContext(Dispatchers.Main) {
                    stockData = newData
                    apiError = null
                }
            } catch (exception: Exception) {
                withContext(Dispatchers.Main) {
                    apiError = exception.localizedMessage
                }
            }
        }
    }

    Column {
        Column() {
            TopBarGoBack(stringResource(R.string.stockview_details), navController = navController)
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
                        if (stockData.isNotEmpty() && yearStockData.isNotEmpty()) {

                            val ytd = getytd(stockData, yearStockData)

                            Text(
                                text = "USD ${"%.2f".format(getcurrentvalue(stockData))}%",
                                modifier = Modifier.padding(end = 20.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(text = "$ytd", style = MaterialTheme.typography.bodyLarge)
                        } else {
                            Text(
                                text = "Loading...",
                                modifier = Modifier.padding(end = 20.dp),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
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
                            .padding(horizontal = 4.dp)
                            .wrapContentWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =  if (activeButton == intervalOption) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(text = intervalToLabel(intervalOption))
                    }
                }
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
            Column() {
                Row() {
                    Column(modifier = Modifier.padding(end = 24.dp)) {
//                        Text(
//                            text = "Opening",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
                        Text(
                            text = stringResource(R.string.stockview_high),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = stringResource(R.string.stockview_low),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column(modifier = Modifier.padding(end = 36.dp)) {
                        if (stockData.isNotEmpty()) {
                            val high = "%.2f".format(findMaxValue(stockData))
                            val low = "%.2f".format(findMinValue(stockData))

//                            Text(
//                                text = "x",
//                                style = MaterialTheme.typography.bodyMedium
//                            )
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
//                        Text(
//                            text = "Prev. close",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
                        Text(
                            text = stringResource(R.string.stockview_high_52w),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = stringResource(R.string.stockview_low_52w),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Column() {
                        val highyear ="%.2f".format(findMaxValue(yearStockData))
                        val lowyear = "%.2f".format(findMinValue(yearStockData))
//                        Text(
//                            text = "X",
//                            style = MaterialTheme.typography.bodyMedium
//                        )
                        Text(
                            text = "$highyear",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = "$lowyear",
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
                        Text(text = "${"%.2f".format(stockData.lastOrNull()?.first?: 0f)}", style = MaterialTheme.typography.titleMedium)
                    }
                    Button(
                        modifier = Modifier
                            .padding(horizontal = 28.dp)
                            .fillMaxWidth(),
                        onClick = { navController.navigate(Screen.BuyScreen1.route) },) {
                        Text(text = stringResource(R.string.stockview_trade_button), style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }

}
/*
@Preview (showBackground = true)
@Composable
fun StockViewScreenPreview() {
    StockViewScreen(navController = rememberNavController(), stockViewModel = StockViewModel(stockRepository = StockRepository(
        LocalContext.current, stockApi = StockApi(LocalContext.current)
    )), "NOVO")
}

 */