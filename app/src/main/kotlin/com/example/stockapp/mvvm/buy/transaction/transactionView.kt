package com.example.stockapp.mvvm.buy.transaction
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockapp.ui.theme.Transaction

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

@Composable
fun TransactionScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TransactionLayout(navController)
    }
}

@Composable
fun TransactionLayout(navController: NavController) {
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
                    text = AnnotatedString(stringResource(R.string.transaction_orders)),
                    onClick = { navController.navigate(Screen.OrderScreen.route) },
                    style = TextStyle(fontSize = 24.sp)
                )
                ClickableText(
                    text = AnnotatedString(stringResource(R.string.transaction_transactions)),
                    onClick = {

                    },
                    style = TextStyle(fontSize = 24.sp, color = Color.Blue)
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

            transactionsColumn()
        }
    }
}

@Composable
fun transactionsColumn(){
    Divider(
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    )
    Transaction( name="NOVO",status="sold", quantity="420", rate="360", amount="21.42")
    repeat(6) {
        Transaction( name="APPL",status="bought", quantity="360", rate="420", amount="69")

    }
}

@Composable
@Preview
fun TransactionScreenPreview() {
    val navController = rememberNavController()
    TransactionScreen(navController)
}