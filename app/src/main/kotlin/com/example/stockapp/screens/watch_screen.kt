package com.example.stockapp.screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import com.example.stockapp.stockApi.ShowStockists
import com.example.stockapp.stockApi.getcurrentvalue
import com.example.stockapp.stockApi.getytd
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
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


    var stockNames = listOf("NOVO", "OIL", "AAPL")

    Box(modifier = Modifier.fillMaxSize()) {


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

                if (stockNames.isNotEmpty()) {
                    ShowStockists(navController, stockNames)
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