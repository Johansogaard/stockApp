package com.example.stockapp.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockapp.ui.theme.StockComposable

import androidx.compose.material3.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import androidx.compose.runtime.*

import com.example.stockapp.stockApi.ApiManager
import com.example.stockapp.stockApi.Stock

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
    Box(modifier = Modifier.fillMaxSize()) {
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            val listOfStocks = ApiManager.getPopularStocks()
            /*for(i in 1..listOfStocks.size)
            {
                val stock = listOfStocks[i-1]
                println(stock.name)
                println(stock.c)
            }*/
            /*items(count = listOfStocks.size) { countValue ->
                val stock = listOfStocks[countValue]
                StockComposable(country ="mikkel", text =stock.name , price =stock.c.toString(), perftdy=stock.dp.toString())

            }*/
           /* StockComposable(country ="mikkel", text ="Johan", price ="3", perftdy ="-11")
            StockComposable(country ="mikkel", text ="Johan", price ="3", perftdy ="-11")
            StockComposable(country ="mikkel", text ="Johan", price ="3", perftdy ="-11")
            StockComposable(country ="mikkel", text ="Johan", price ="3", perftdy ="-11")

            StockComposable(country ="mikkel", text ="Johan", price ="3", perftdy ="-11")*/

        }
    }
}

@Composable
fun stocklist()
{

    val listForFirstFunction: List<() -> Unit> = listOf()


}




@Composable
@Preview
fun WatchScreenPreview() {
    val navController = rememberNavController()
    WatchScreen(navController)
}