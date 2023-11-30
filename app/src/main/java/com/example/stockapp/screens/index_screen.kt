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
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import com.example.stockapp.R
import com.example.stockapp.stockApi.ShowStockists
import com.example.stockapp.stockApi.getGroupTickers
import com.example.stockapp.stockApi.getcurrentvalue
import com.example.stockapp.stockApi.getytd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.stockapp.ui.theme.Stock

@Stable
var activeButton by mutableStateOf("WORLD")



@Composable
fun IndexScreen(navController: NavController) {
    val coroutineScope = rememberCoroutineScope()
    var stockNames by remember { mutableStateOf<List<String>>(emptyList()) }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Today's stock market",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight(800))
            )

            ButtonRow { selectedButton, _ ->
                activeButton = selectedButton
            }

            LaunchedEffect(activeButton) {
                coroutineScope.launch(Dispatchers.IO) {
                    stockNames = getGroupTickers(activeButton)
                    println(stockNames)
                }
            }

            if (stockNames.isNotEmpty()) {
                ShowStockists(navController, stockNames)
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
        IndexButton(text = "S&P500", picture = "usa.png", onClick = { text, picture -> onButtonClick(text, picture) })
        IndexButton(text = "C25", picture = "dk.png", onClick = { text, picture -> onButtonClick(text, picture) })
    }
}

@Composable
fun IndexButton(
    text: String,
    modifier: Modifier = Modifier,
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



@Preview
@Composable
fun IndexScreenPreview() {
    val navController = rememberNavController()
    IndexScreen(navController)
}
