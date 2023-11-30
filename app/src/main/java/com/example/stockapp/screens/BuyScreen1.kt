package com.example.stockapp.screens
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
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown

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

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import com.example.stockapp.data.Screen

import com.example.stockapp.R
const val NUMPAD_BUTTON_ONE = 1
const val NUMPAD_BUTTON_TWO = 2
const val NUMPAD_BUTTON_THREE = 3
const val NUMPAD_BUTTON_FOUR = 4
const val NUMPAD_BUTTON_FIVE = 5
const val NUMPAD_BUTTON_SIX = 6
const val NUMPAD_BUTTON_SEVEN = 7
const val NUMPAD_BUTTON_EIGHT = 8
const val NUMPAD_BUTTON_NINE = 9
const val NUMPAD_BUTTON_ZERO = 0
@Composable
fun BuyScreen1(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)

    )
    {
        BuyLayout1(navController)

    }

}


@Composable
fun BuyLayout1(navController: NavController)
{
    var textState by remember { mutableStateOf("") }

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
                        contentDescription = "Back")

                }
                Spacer(modifier = Modifier.weight(0.5f))
                Text(
                    text = "Preview Buy",
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
                        contentDescription = "stringResource(id = R.string.dog_content_description)"
                    )  }
                Spacer(modifier = Modifier.weight(1f))


                Column () {

                    Row {
                        Text(text = "OMX Copenhagen 25")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Row {
                        Text(text = "Index NASDAQ: OMXC25")
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(modifier = Modifier.height(48.dp), verticalArrangement=Arrangement.Center ){
                    Text(text = "Buy in DKK")
                }


            }

            Spacer(modifier = Modifier.size(10.dp))

            Divider(thickness = 1.dp, color = Color.Gray)

            Spacer(modifier = Modifier.size(10.dp))

            ButtonWithBorder()

            // Buy Button with Dropdown
            // Implement the button and dropdown for buying stocks

            Spacer(modifier = Modifier.weight(0.5f))
            CustomTextField(textState) { newValue ->
                textState = newValue
            }
            // Amount of DKK TextField


            Spacer(modifier = Modifier.height(20.dp))

            // Cash Available
            Text(text = "Balance cash available: 24.555 DKK", fontSize = 14.sp,modifier=Modifier.padding(bottom=20.dp)
            )
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
                Text(text = "Continue", modifier = Modifier.padding(7.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))

            Numpad { digit ->
                if (textState.filter { it.isDigit() }.length < 7) {
                    textState += digit.toString()
                }
            }



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
            NumpadButton(NUMPAD_BUTTON_ONE, onDigitClick)
            NumpadButton(NUMPAD_BUTTON_TWO, onDigitClick)
            NumpadButton(NUMPAD_BUTTON_THREE, onDigitClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumpadButton(NUMPAD_BUTTON_FOUR, onDigitClick)
            NumpadButton(NUMPAD_BUTTON_FIVE, onDigitClick)
            NumpadButton(NUMPAD_BUTTON_SIX, onDigitClick)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NumpadButton(NUMPAD_BUTTON_SEVEN, onDigitClick)
            NumpadButton(NUMPAD_BUTTON_EIGHT, onDigitClick)
            NumpadButton(NUMPAD_BUTTON_NINE, onDigitClick)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            NumpadButton(NUMPAD_BUTTON_ZERO, onDigitClick)
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
fun CustomTextField(value: String, onValueChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = LocalConfiguration.current.screenHeightDp.dp * 0.2f)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BasicTextField(
                        value = value,
                        onValueChange = {
                            if (it.filter { char -> char.isDigit() }.length <= 7) {
                                onValueChanged(it)
                            }
                        },
                        modifier = Modifier.width(IntrinsicSize.Min), // Use wrapContentWidth here
                        textStyle = TextStyle(fontSize = 45.sp, color = Color.Black, textAlign = TextAlign.End), // Align text to the end
                        cursorBrush = SolidColor(Color.Black)
                    )
                    Text(text = if (value.isNotEmpty()) "kr." else "", fontSize = 45.sp, modifier = Modifier.wrapContentWidth()) // Use wrapContentWidth here
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxHeight()
            )
        }
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
                text = "Buy in DKK",
                color = Color(0xFF1A65E7))
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "down",
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