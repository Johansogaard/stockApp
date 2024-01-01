package com.example.stockapp.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.stockapp.utils.formatNumberUtility
import com.example.stockapp.viewModels.BuyViewModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.NumberFormat
import java.util.Locale

@Composable
fun BuyScreen2(navController: NavController, buyViewModel: BuyViewModel = viewModel()) {
    val currentAmount = buyViewModel.currentAmount
    val marketPrice = buyViewModel.marketPrice.collectAsState().value
    val totalCost = buyViewModel.calculateTotalCost(currentAmount.toInt())
    val tradingFee = buyViewModel.tradingFee
    val numShares = buyViewModel.calculateNumberOfShares(currentAmount.toInt(), marketPrice)

    fun formatShares(numberOfShares: Double): String {
        val format = NumberFormat.getNumberInstance(Locale.GERMANY)
        format.maximumFractionDigits = 4
        return format.format(numberOfShares)
    }



    val formattedMarketPrice = formatNumberUtility.formatNumberWithDecimal(marketPrice)
    val formattedCurrentAmount = formatNumberUtility.formatNumberWithDecimal(currentAmount.toDouble())
    val formattedTotalCost = formatNumberUtility.formatNumberWithDecimal(totalCost.toDouble())
    val formattedTradingFee = formatNumberUtility.formatNumberWithDecimal(tradingFee.toDouble())

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {

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
                        contentDescription = stringResource(R.string.common_back)
                    )

                }
                Spacer(modifier = Modifier.weight(0.75f))
                Text(
                    text = stringResource(R.string.buy_previous),
                    fontSize = 20.sp, fontWeight=FontWeight.Bold ,
                    modifier = Modifier.padding(end=150.dp)

                )

            }


            Spacer(modifier = Modifier.size(10.dp))


            Row (modifier = Modifier
                .width(320.dp)
                .height(48.dp)){
                Column (modifier = Modifier.padding(end=5.dp)){
                    Image(
                        painter = painterResource(id = R.drawable.dk),
                        contentDescription = ""
                    )  }
                Spacer(modifier = Modifier.weight(1f))


                Column () {

                    Row {
                        Text(text = stringResource(R.string.buy_omx_copenhagen))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Text(text = stringResource(R.string.buy_nasdaq))
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier.height(48.dp), verticalArrangement=Arrangement.Center ){
                    Text(text = stringResource(R.string.buy_in_dkk))
                }


            }
            Spacer(modifier = Modifier.size(10.dp))

            Divider(thickness = 1.dp, color = Color.Gray)

            Spacer(modifier = Modifier.weight(0.5f))
            Box(
                modifier = Modifier
                    .height(300.dp)
                    .width(320.dp)
                    .clip(shape)
                    .align(Alignment.CenterHorizontally)
                    .background(Color(0xFFFAFAFA))
            )

            // Set the background color to blue


            {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                ) {
                    Column() {
                        Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                            Text(text = stringResource(R.string.buy_market_price),            modifier = Modifier.weight(1f) // Push the text to the end
                            )

                            Text(text = "DKK $formattedMarketPrice",fontWeight = FontWeight.Bold)
                        }
                        Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                            Text(text = stringResource(R.string.buy_num_of_shares),            modifier = Modifier.weight(1f) // Push the text to the end
                            )

                            Text(text = formatShares(numShares),fontWeight = FontWeight.Bold)
                        }
                        Divider(thickness = 1.dp, color = Color.Gray)

                        Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                            Text(text = stringResource(R.string.buy_amount),            modifier = Modifier.weight(1f) // Push the text to the end
                            )

                            Text(text ="DKK " + formattedCurrentAmount ,fontWeight = FontWeight.Bold)
                        }
                        Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                            Text(text = stringResource(R.string.buy_trading_fee),           modifier = Modifier.weight(1f) // Push the text to the end
                            )

                            Text(text = "DKK $formattedTradingFee",fontWeight = FontWeight.Bold)
                        }

                        Divider(thickness = 1.dp, color = Color.Gray)


                        Row (modifier = Modifier.padding(bottom=15.dp,top=15.dp)){
                            Text(text = stringResource(R.string.buy_total),            modifier = Modifier.weight(1f) // Push the text to the end
                            )

                            Text(text = "DKK $formattedTotalCost",fontWeight = FontWeight.Bold)
                        }
                    }

                }


            }
            Spacer(modifier = Modifier.weight(0.5f))

            Button(
                onClick = {
                    navController.navigate(Screen.BuyScreen3.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A65E7)
                ),
                modifier = Modifier.width(320.dp) // Set the width to 320.dp
            ) {
                Text(text = stringResource(R.string.buy_buy_now_button), modifier = Modifier.padding(7.dp))
            }

            Spacer(modifier = Modifier.size(50.dp))

        }}

    }



@Composable
@Preview
fun BuyScreen2Preview() {
    val navController = rememberNavController()
    BuyScreen2(navController)
}