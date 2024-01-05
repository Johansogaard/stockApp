package com.example.stockapp.screens

import SellUiState
import SellViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.data.Screen
import androidx.compose.material3.Typography

// ... imports ...

@Composable
fun SellScreen(navController: NavController, sellViewModel: SellViewModel = viewModel()) {
    val sellUiState by sellViewModel.uiState.collectAsState()
    val currentAmount = sellViewModel.currentAmount
    var dialogState by remember {
        mutableStateOf(Triple(false, "", ""))
    } // (showDialog, title, message)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ... Your code for the top part of the screen ...
            Spacer(modifier = Modifier.size(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }, modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.CenterVertically)

                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = stringResource(R.string.common_back)
                    )

                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = stringResource(R.string.sell),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)


                )
                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(48.dp))

            }
            Spacer(modifier = Modifier.size(10.dp))

            Row(
                modifier = Modifier
                    .width(320.dp)
                    .height(48.dp)
            ) {
                Column(modifier = Modifier.padding(end = 5.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.dk),
                        "contentDescription = stringResource(id = R.string.dog_content_description)"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))


                Column() {

                    Row {
                        Text(text = stringResource(R.string.buy_omx_copenhagen))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Text(text = stringResource(R.string.buy_nasdaq))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.height(48.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Buy in DKK")
                }


            }

            Spacer(modifier = Modifier.size(10.dp))

            Divider(thickness = 1.dp, color = Color.Gray)
            Spacer(modifier = Modifier.size(10.dp))
            Box(
                modifier = Modifier
                    .height(138.dp)
                    .width(345.dp)
                    .clip(AlertDialogDefaults.shape)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFFF6F5F5))
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(), // Make the Column fill the Box
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row {
                        Text(text = "Quantity", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "26,67",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                    Row {
                        Text(text = "Profit/Loss", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        //red or green color depending on profit/loss
                        Text(
                            text = "-1.506,77 kr.(-8,01%)",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )

                    }

                    Row {
                        Text(text = "Proceeds", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "10.000,00 kr.",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }

                    Row {
                        Text(text = "Avg. price", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "1.605,77 kr.",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(20.dp))

            Row(
                Modifier
                    .width(345.dp)
                    .height(104.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    val buttonModifier = Modifier
                        .height(23.dp).width(39.dp)
                        .border(1.dp, Color(0xFF1A65E7), RoundedCornerShape(10.dp))

                    val buttonColors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.DarkGray
                    )
                    Row {
                        Text(text = "Selling", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        Button(onClick = { /*TODO*/ }, colors = buttonColors, modifier = buttonModifier) {
                            Text(text = "25%", color = Color.DarkGray)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = { /*TODO*/ }, colors = buttonColors, modifier = buttonModifier) {
                            Text(text = "50%", color = Color.DarkGray)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = { /*TODO*/ }, colors = buttonColors, modifier = buttonModifier) {
                            Text(text = "75%", color = Color.DarkGray)
                        }
                        Spacer(modifier = Modifier.size(8.dp))
                        Button(onClick = { /*TODO*/ }, colors = buttonColors, modifier = buttonModifier) {
                            Text(text = "100%", color = Color.DarkGray)
                        }
                    }
                    Row {
                        Text(text = "Quantity", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A65E7)),
                            modifier = Modifier
                                .width(39.dp).height(23.dp)
                        ) {
                            Text(text = "100%")
                        }
                        Text(
                            text = "26,67",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                        Button(onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A65E7)),
                            modifier = Modifier
                                .width(29.dp).height(23.dp)
                        ) {
                            Text(text = "100%")
                        }
                    }
                    Row {
                        Text(text = "Price", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "1.605,77 kr.",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            // continue Button
            Button(
                onClick = {
                    navController.navigate(Screen.BuyScreen2.route)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A65E7)),
                modifier = Modifier
                    .width(345.dp)
                    .padding(0.dp) // Adjust padding as needed
            ) {
                Text(
                    text = stringResource(R.string.common_continue),
                    modifier = Modifier.padding(7.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Reuse the Numpad Composable as it is
            Numpad(onDigitClick = {
                sellViewModel.updateAmount(it)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SellScreenPreview() {
    val navController = rememberNavController() // Mock NavController
    val sellViewModel = SellViewModel(SellUiState()) // Create a mock ViewModel instance

    SellScreen(navController = navController, sellViewModel = sellViewModel)
}
