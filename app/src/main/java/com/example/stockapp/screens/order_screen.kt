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
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign

import com.example.stockapp.R
import com.example.stockapp.data.Screen
import com.example.stockapp.ui.theme.Transaction

@Stable

@Composable
fun OrderScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        OrderLayout(navController)
    }
}

@Composable
fun OrderLayout(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom=4.dp, top = 24.dp),
            ) {
                ClickableText(
                    text = AnnotatedString("Orders"),
                    onClick = {  },
                    style = TextStyle(fontSize = 24.sp, color = Color.Blue)
                )
                ClickableText(
                    text = AnnotatedString("Transactions"),
                    onClick = {
                        navController.navigate(Screen.TransactionScreen.route)
                    },
                    style = TextStyle(fontSize = 24.sp)
                )
            }

            /*
            Text(
                text = "Transactions",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight(800)
                )
            )
*/

            orderColumn()
        }
    }
}

@Composable
fun orderColumn(){
    Divider(
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    )
    Transaction( name="Johan",status="order", quantity="420", rate="360", amount="21.42")
    repeat(6) {
        Transaction( name="mikkel",status="order", quantity="360", rate="420", amount="69")

    }
}




@Composable
@Preview
fun OrderScreenPreview() {
    val navController = rememberNavController()
    OrderScreen(navController)
}