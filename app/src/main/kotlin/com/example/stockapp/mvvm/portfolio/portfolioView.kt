package com.example.stockapp.mvvm.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockapp.R



@Composable
fun PortfolioScreen(navController: NavController, portfolioViewModel: PortfolioViewModel) {
    val uiState by portfolioViewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        PortfolioLayout(
            navController = navController,
            portfolioViewModel = portfolioViewModel,
            uiState = uiState,
        )
    }
}

@Composable
fun PortfolioLayout(navController: NavController, portfolioViewModel: PortfolioViewModel, uiState: PortfolioUiState) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.size(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }, modifier = Modifier
                        .size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )

                }
                Spacer(modifier = Modifier.weight(1.2f))
                Text(
                    text = "Portfolio",
                    fontSize = 30.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 90.dp)
                )
                //Spacer(modifier = Modifier.weight(0.90f))
                IconButton(
                    onClick = {
                        portfolioViewModel.signOut()
                    },

                    ) {
                    Icon(
                        imageVector = Icons.Rounded.ExitToApp,
                        contentDescription = "exit app icon",
                        tint = Color.Black // You can change the icon color here
                    )
                }
            }
            Spacer(modifier = Modifier.size(30.dp))

            Row(
                modifier = Modifier
                    .width(320.dp)
                    .height(90.dp)
            ) {
                //Spacer(modifier = Modifier.weight(1f))
                Column() {


                    Row {
                        Text(text = "Overview",
                            fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.weight(0.1f))
                    Row {
                        Text(text = "Equity Capital",
                            fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.weight(0.1f))
                    Row {
                        Text(text = "122.388",
                            fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))


            }
            Spacer(modifier = Modifier.size(30.dp))


            Image(
                painter = painterResource(id = R.drawable.stock_graph_preview),
                contentDescription = "stringResource(id = R.string.dog_content_description)",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(40.dp))
            Divider(thickness = 1.dp, color = Color.Gray)
            Spacer(modifier = Modifier.size(10.dp))
            Row (modifier = Modifier
                .width(320.dp)
                .height(30.dp)) {
                Text(text = "Tilgængelig for handel")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "22.232 DKK")
            }
            Spacer(modifier = Modifier.size(10.dp))
            Divider(thickness = 1.dp, color = Color.Gray)

        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun PreviewPortfolioScreen() {
    PortfolioScreen(navController = rememberNavController(), portfolioViewModel = PortfolioViewModel(stockRepository = StockRepository()))
}*/