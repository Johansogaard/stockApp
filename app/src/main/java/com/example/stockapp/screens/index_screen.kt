package com.example.stockapp.screens
import android.util.Log
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
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign

import com.example.stockapp.R
import com.example.stockapp.data.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockapp.viewModels.IndexViewModel


@Stable
var activeButton by mutableStateOf("")


@Composable
fun IndexScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        IndexLayout(navController)
    }
}

@Composable
fun IndexLayout(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Today's stock market",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800)
                )
            )
            ButtonRow()

            StockColumn(navController)
}
        }
    }


        @Composable
        fun StockColumn(navController: NavController, viewModel: IndexViewModel = viewModel()) {

            Column {
                if (activeButton=="USA") {

                        viewModel.usaStocks.value.forEach { stockInfo ->
                            Stock(
                                country = "mikkel", // Replace with the actual country code
                                text = stockInfo.symbol,
                                price = stockInfo.currentPrice,
                                perftdy = stockInfo.previousClosePrice, // You might want to calculate the performance based on `currentPrice` and `previousClosePrice`
                                onclick = { navController.navigate(Screen.StockViewScreen.route) }
                            )
                            Text("hey")
                        }
                    Text("hey")
                    }

       else {
            Stock(country="mikkel", text="Johan", price="3", perftdy="-11", onclick = {navController.navigate(Screen.StockViewScreen.route)})

            Stock(country="mikkel", text="Johan", price="3", perftdy="-11", onclick = {navController.navigate(Screen.StockViewScreen.route)})
            Stock(country="mikkel", text="Mikkel", price="420", perftdy="69", onclick = {navController.navigate(Screen.StockViewScreen.route)})


        }


    }
}



@Composable
fun ButtonRow() {
    Row(
        modifier = Modifier.padding(16.dp)
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween
    ) {

        IndexButton(
            text = "World",
            picture = "mikkel.jpg",
        )
        IndexButton(
            text = "USA",
            picture = "mikkel.jpg",
        )
        IndexButton(
            text = "Denmark",
            picture = "mikkel.jpg",
        )
    }
}

@Composable
fun IndexButton(
    text: String,
    modifier: Modifier = Modifier,
    picture: String,
) {
    Button(
        onClick = { activeButton=text },
        modifier = modifier.then(Modifier.width(110.dp).height(40.dp)),
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

            Spacer(modifier = Modifier.weight(1f))  // This spacer will take up all available space to the left of the text

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.align(Alignment.CenterVertically)  // This modifier is optional as verticalAlignment is already set in the Row
            )

            Spacer(modifier = Modifier.weight(1f))  // This spacer will take up all available space to the right of the text
        }

    }
}



@Composable
@Preview

fun IndexScreenPreview() {
    val navController = rememberNavController()
    IndexScreen(navController)

}