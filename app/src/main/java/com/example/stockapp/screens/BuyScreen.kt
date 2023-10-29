package com.example.stockapp.screens
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.KeyboardType

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.Screen
import com.example.stockapp.authentication.EmailAuthManager
import com.google.android.gms.wallet.button.ButtonConstants
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardCapitalization

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun BuyScreen(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)

    )
    {
        BuyLayout(navController)

    }

}
@Composable
fun BuyLayout(navController: NavController)
{
    var moneyText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Back Button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        // Add code to navigate back to the previous page
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

               Text(
                    text = "Buy",
                    fontSize = 20.sp ,
                    modifier = Modifier.padding(end=180.dp)

                )


            }

            // Stock Details
            // Add the stock logo, name, where it is sold, and the ticker symbol here

            // Market Price
            Text(text = "Market Price: XX", fontSize = 16.sp)

            // Buy Button with Dropdown
            // Implement the button and dropdown for buying stocks

            Spacer(modifier = Modifier.weight(1f))
            MoneyTextField { newMoneyText ->
                moneyText = newMoneyText
            }
            // Amount of DKK TextField



            // Cash Available
            Text(text = "Balance cash available: 24.555 DKK", fontSize = 14.sp,modifier=Modifier.padding(bottom=20.dp)
                )
            // Continue Button
            Button(

                onClick = {
                    // Add code to handle the buy action
                },colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A65E7))

            ) {
                Text(text = "Continue", modifier = Modifier
                    .padding(120.dp,7.dp,120.dp,7.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

            Numpad(onDigitClick = { digit ->
                val newText = moneyText + digit.toString()
                moneyText = newText // Update the moneyText variable
            })

            // Numpad
            // Implement the numpad layout, including digits and a backspace button
        }
    }

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
                .padding(0.dp, 0.dp, 0.dp, 35.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyTextField(onValueChanged: (String) -> Unit) {
    val textState = remember { mutableStateOf("DKK 0") }

    TextField(
        value = textState.value,
        onValueChange = {
            if (it.startsWith("DKK ")) {
                textState.value = it
                onValueChanged(it)
            } else if (it.isEmpty()) {
                textState.value = "DKK 0"
                onValueChanged("0")
            } else {
                textState.value = "DKK $it"
                onValueChanged(it)
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier.fillMaxWidth()
    )
}










@Composable
@Preview
fun BuyScreenPreview() {
    val navController = rememberNavController()
    BuyScreen(navController)
}