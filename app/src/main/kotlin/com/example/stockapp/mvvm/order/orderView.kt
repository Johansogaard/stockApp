package com.example.stockapp.mvvm.order
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material3.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.*

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString

import com.example.stockapp.R
import com.example.stockapp.mvvm.Screen
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
                    .padding(start = 16.dp, bottom = 4.dp, top = 24.dp),
            ) {
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.order_orders)),
                    onClick = {  },
                    style = TextStyle(fontSize = 24.sp, color = Color.Blue)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.order_transactions)),
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
    Transaction( name="NOVO",status="order", quantity="420", rate="360", amount="21.42")
    repeat(6) {
        Transaction( name="APPL",status="order", quantity="360", rate="420", amount="69")

    }
}




@Composable
@Preview
fun OrderScreenPreview() {
    val navController = rememberNavController()
    OrderScreen(navController)
}