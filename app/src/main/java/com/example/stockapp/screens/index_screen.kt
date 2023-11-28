package com.example.stockapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.example.stockapp.R
import com.example.stockapp.data.Screen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.stockapp.ui.theme.Stock

@Stable
var activeButton by mutableStateOf("")
var activeButtonPicture by mutableStateOf("mikkel.jpg") // Default picture

suspend fun getStockPrice(ticker: String, interval:String): String {
    val url = URL("http://10.0.2.2:8080/stock/$ticker/$interval/1")
    val httpURLConnection = url.openConnection() as HttpURLConnection
    httpURLConnection.requestMethod = "GET"

    val inputStream = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
    val gson = Gson()
    val listType = object : TypeToken<List<Triple<Double, Double, Double>>>() {}.type
    val result: List<Triple<Double, Double, Double>> = gson.fromJson(inputStream, listType)

    return if (result.isNotEmpty()) result.first().first.toString() else "No Data"
}

@Composable
fun IndexScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var stockNames by remember { mutableStateOf<List<String>>(emptyList()) }
    var stockPrices by remember { mutableStateOf(mutableMapOf<String, String>()) }
    var stockStarts by remember { mutableStateOf(mutableMapOf<String, String>()) }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Today's stock market",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800))
            )

            ButtonRow { selectedButton, selectedButtonPicture ->
                activeButton = selectedButton
                activeButtonPicture = selectedButtonPicture
                coroutineScope.launch(Dispatchers.IO) {
                    stockNames = getGroupTickers(selectedButton)


                    stockNames.forEach { stockName ->
                        val priceString = getStockPrice(stockName, "MINUTE")
                        val startString = getStockPrice(stockName, "DAY")
                        val price = priceString.toDoubleOrNull() ?: 0.0
                        val start = startString.toDoubleOrNull() ?: 0.0
                        val change = price - start

                        stockPrices[stockName] = priceString
                        println(stockPrices[stockName])
                        println(stockPrices)
                        stockStarts[stockName] = "%.2f".format(change)
                    }
                }
            }

            stockNames.forEach { stockName ->
                println(stockPrices[stockName])
                println(stockPrices)
                val price = stockPrices[stockName] ?: "Fetching..."
                val change = stockStarts[stockName] ?: "Fetching..."
                Stock(
                    picture = activeButtonPicture,
                    text = stockName,
                    price = price,
                    perftdy = change,
                    onclick = { navController.navigate(Screen.StockViewScreen.route) }
                )
            }
        }
    }
}

@Composable
fun ButtonRow(onButtonClick: (String, String) -> Unit) {
    Row(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IndexButton(text = "WORLD", picture = "mikkel.jpg", onClick = { text, picture -> onButtonClick(text, picture) })
        IndexButton(text = "S&P500", picture = "usa.jpg", onClick = { text, picture -> onButtonClick(text, picture) })
        IndexButton(text = "C25", picture = "dk.png", onClick = { text, picture -> onButtonClick(text, picture) })
    }
}


@Composable
fun IndexButton(
    text: String,
    picture: String,
    onClick: (String, String) -> Unit
) {
    Button(
        onClick = { onClick(text, picture) }, // Pass both text and picture
        modifier = Modifier.width(110.dp).height(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (activeButton == text) Accent else Color.Red
        ),
        contentPadding = PaddingValues(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable::class.java.getDeclaredField(picture.substringBeforeLast(".")).getInt(null)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
                    .background(Accent, CircleShape)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

suspend fun getGroupTickers(groupName: String): List<String> {
    val url = URL("http://10.0.2.2:8080/group/tickers/$groupName")
    val httpURLConnection = url.openConnection() as HttpURLConnection
    httpURLConnection.requestMethod = "GET"

    val inputStream = httpURLConnection.inputStream.bufferedReader().use { it.readText() }
    val gson = Gson()
    val listType = object : TypeToken<List<String>>() {}.type
    return gson.fromJson(inputStream, listType)
}

@Preview
@Composable
fun IndexScreenPreview() {
    val navController = rememberNavController()
    IndexScreen(navController)
}
