package com.example.stockapp.mvvm.order
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.stockapp.R
import com.example.stockapp.mvvm.Screen

@Stable

@Composable
fun OrderScreen(navController: NavController) {
    val orderViewModel: OrderViewModel = hiltViewModel()
    val uiState by orderViewModel.uiState.collectAsState()

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
            LazyColumn() {
                item {
                    repeat(20) {
                        orderColumn(
                            orderViewModel = orderViewModel,
                            uiState = uiState
                        )
                    }
                }
            }
        }
    }

}


@Composable
fun orderColumn(orderViewModel: OrderViewModel, uiState: OrderUiState){
    Box(modifier = Modifier.clickable {  }) {
        Divider(
            color = Color.Black,
            modifier = Modifier.fillMaxWidth()
        )

        Transaction(name = uiState.title, status = "order", quantity = "360", rate = "420", amount = "69")
    }
}

@Composable
fun Transaction(
    name: String,
    status: String,
    quantity: String,
    rate: String,
    amount: String
) {Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top = 4.dp)
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Canvas(modifier = Modifier.size(12.dp)) {
                drawCircle(
                    color = when (status) {
                        "sold" -> Color.Green
                        "bought" -> Color.Blue
                        "order" -> Color.Gray
                        else -> Color.Gray
                    }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = status,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Text(
            text = "date",
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.Gray
            ),
        )
    }


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.End

        ) {
            Text(
                text = "quantity",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.Gray
                ),
            )
            Text(
                text = quantity,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }

        Column(
            horizontalAlignment = Alignment.End

        )
        {
            Text(
                text = "Exchange rate(DKK)",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = Color.Gray
                ),
            )
            Text(
                text = rate,

                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),

                )
        }
        Column(
            horizontalAlignment = Alignment.End
        )
        {
            Text(
                text = "Amount(DKK)",
                style = TextStyle(
                    fontSize = 15.sp,
                ),
                color = Color.Gray
            )
            Text(
                text = amount,

                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }

}
    Divider(
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    )
}




@Composable
@Preview
fun OrderScreenPreview() {
    val navController = rememberNavController()
    OrderScreen(navController)
}