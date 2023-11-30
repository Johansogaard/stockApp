package com.example.stockapp.mvvm.screens
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.stockapp.R
import com.example.stockapp.mvvm.viewModels.BuyViewModel

@Composable
fun BuyScreen1(navController: NavController, buyViewModel: BuyViewModel = viewModel()) {
    val buyUiState by buyViewModel.uiState.collectAsState()

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
                        contentDescription = stringResource(R.string.common_back))

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


            }

            Spacer(modifier = Modifier.size(10.dp))

            Divider(thickness = 1.dp, color = Color.Gray)

            Spacer(modifier = Modifier.size(80.dp))

            ButtonWithBorder()

            // Buy Button with Dropdown
            // Implement the button and dropdown for buying stocks

            Spacer(modifier = Modifier.weight(1f))
            MoneyTextField(
                isValueOverMax = buyUiState.isMaxAmount,
                currentAmount = buyViewModel.currentAmount,
            )


            Spacer(modifier = Modifier.height(20.dp))

            // Cash Available
            if (buyUiState.isMaxAmount) {
                Text(
                    text = stringResource(R.string.buy_funds_available) + buyUiState.balance,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
            else {
                Text(
                    text = stringResource(R.string.buy_funds_available) + buyUiState.balance,
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
            // Continue Button
            Button(
                onClick = {
                    navController.navigate(Screen.BuyScreen2.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A65E7)
                ),
                modifier = Modifier.width(320.dp) // Set the width to 320.dp
            ) {
                Text(text = stringResource(R.string.common_continue), modifier = Modifier.padding(7.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

            Numpad(onDigitClick = {
                buyViewModel.updateAmount(it)
            })

            // Numpad
            // Implement the numpad layout, including digits and a backspace button
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyTextField(isValueOverMax: Boolean,
                   currentAmount: String,
                   modifier: Modifier = Modifier) {

    BasicTextField(
        value = currentAmount,
        onValueChange = {  },
        textStyle = TextStyle(Color.Black, fontSize = 48.sp, fontWeight = FontWeight.SemiBold),
        cursorBrush = SolidColor(Color.Black),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(140.dp)
                    .border(4.dp, Color(0xFF1A65E7), RoundedCornerShape(35.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    innerTextField()

                }
            }
        }
    )
}

@Composable
fun Numpad(onDigitClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFAFAFA)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumpadButton(1, onDigitClick)
            NumpadButton(2, onDigitClick)
            NumpadButton(3, onDigitClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumpadButton(4, onDigitClick)
            NumpadButton(5, onDigitClick)
            NumpadButton(6, onDigitClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumpadButton(7, onDigitClick)
            NumpadButton(8, onDigitClick)
            NumpadButton(9, onDigitClick)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            NumpadButton(0, onDigitClick)
            Spacer(modifier = Modifier.weight(0.5f))


        }
    }


}



@Composable
fun NumpadButton(digit: Int, onClick: (Int) -> Unit) {
    Button(
        onClick = { onClick(digit) },
        modifier = Modifier.padding(30.dp,10.dp,30.dp,0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent)

        ) {
        Text(text = digit.toString(),
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight= FontWeight.ExtraBold
            )
    }

}




@Composable
fun ButtonWithBorder() {
    Button(
        onClick = {
            // Your onclick code
        },
        border = BorderStroke(2.dp, Color(0xFF1A65E7)),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF1A65E7)), modifier = Modifier.height(35.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.buy_in_dkk),
                color = Color(0xFF1A65E7))
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
}





@Composable
@Preview
fun BuyScreenPreview() {
    val navController = rememberNavController()
    BuyScreen1(navController)
}