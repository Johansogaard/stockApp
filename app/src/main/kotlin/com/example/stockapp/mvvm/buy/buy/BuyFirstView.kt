package com.example.stockapp.mvvm.buy.buy
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog

import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stockapp.mvvm.Screen

import com.example.stockapp.R
import com.example.stockapp.utils.formatNumberUtility.formatNumberWithoutDecimal
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
@Composable
fun BuyScreen1(navController: NavController, buyViewModel: BuyViewModel = viewModel()) {
    val buyUiState by buyViewModel.uiState.collectAsState()
    val currentAmount = buyViewModel.currentAmount
    var dialogState by remember { mutableStateOf(Triple(false, "", "")) } // (showDialog, title, message)



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
                Spacer(modifier = Modifier.weight(0.5f))
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
                        "contentDescription = stringResource(id = R.string.dog_content_description)"
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
                    Text(text = "Buy in DKK")
                }


            }

            Spacer(modifier = Modifier.size(10.dp))

            Divider(thickness = 1.dp, color = Color.Gray)

            Spacer(modifier = Modifier.size(10.dp))



            Spacer(modifier = Modifier.weight(0.5f))
            CustomTextField(isValueOverMax = buyUiState.isMaxAmount,
                currentAmount = currentAmount,
                onBackspaceClick = { buyViewModel.removeLastDigit() }
            )
            // Amount of DKK TextField


            Spacer(modifier = Modifier.height(20.dp))

            // Cash Available
            if (buyUiState.isMaxAmount) {
                Text(
                    text = stringResource(R.string.buy_funds_available)+ " " + formatNumberWithoutDecimal(buyUiState.balance.toDouble()) + " kr.",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
            else {
                Text(
                    text = stringResource(R.string.buy_funds_available)+ " " + formatNumberWithoutDecimal(buyUiState.balance.toDouble()) + " kr.",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
            }
            // Continue Button
            Button(
                onClick = {
                    val amount = currentAmount.toIntOrNull() ?: 0
                    when {
                        amount < 200 -> {
                            dialogState = Triple(true, "Minimum Amount Required", "The minimum amount is 200 kr.")
                        }
                        amount > buyUiState.balance -> {
                            dialogState = Triple(true, "Insufficient Funds", "You do not have enough funds to make this purchase.")
                        }
                        else -> navController.navigate(Screen.BuyScreen2.route)
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A65E7)
                ),
                modifier = Modifier.width(320.dp) // Set the width to 320.dp
            ) {
                Text(text = stringResource(R.string.common_continue), modifier = Modifier.padding(7.dp))
            }


            if (dialogState.first) {
                AlertDialog(
                    onDismissRequest = { dialogState = Triple(false, "", "") },
                    title = { Text(dialogState.second) },
                    text = { Text(dialogState.third) },
                    confirmButton = {
                        Button(onClick = { dialogState = Triple(false, "", "") },colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A65E7)
                        )) {
                            Text("OK")
                        }
                    }
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            Numpad(onDigitClick = {
                buyViewModel.updateAmount(it)
            })


        }
    }

}

@Composable
fun Numpad(onDigitClick: (Int) -> Unit) {
    // Container with rounded corners and elevation
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Adjust the padding as needed
        shape = RoundedCornerShape(8.dp), // Adjust the corner size as needed
        color = Color(0xFFFAFAFA), // Adjust the background color as needed
        shadowElevation = 4.dp // Adjust the elevation as needed
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NumpadRow(onDigitClick, 1, 2, 3)
            NumpadRow(onDigitClick, 4, 5, 6)
            NumpadRow(onDigitClick, 7, 8, 9)
            NumpadRow(onDigitClick, null,0, null)
        }
    }
}
@Composable
fun NumpadRow(onDigitClick: (Int) -> Unit, vararg digits: Int?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly // This will space the buttons evenly
    ) {
        digits.forEach { digit ->
            if (digit != null) {
                NumpadButton(digit, onDigitClick)
            } else {
                Spacer(modifier = Modifier.width(120.dp).height(60.dp)) // Empty space for alignment
            }
        }
    }
}



@Composable
fun NumpadButton(digit: Int?, onClick: (Int) -> Unit) {
    // Remember a MutableInteractionSource for this button, which allows us to track its interactions
    val interactionSource = remember { MutableInteractionSource() }
    // Collect the isPressed state from the interactionSource
    val isPressed = interactionSource.collectIsPressedAsState().value

    // Determine the button color based on the isPressed state
    val buttonColor = if (isPressed) Color.LightGray else Color.Transparent

    Button(
        onClick = { digit?.let { onClick(it) } },
        modifier = Modifier.width(120.dp).height(60.dp).padding(0.dp, 0.dp, 0.dp, 0.dp)
        ,
        shape= RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor),
        interactionSource = interactionSource,
        elevation = null

    ) {
        Text(text = digit.toString(), // ... existing code ...)
            fontSize = 25.sp,
            color = Color.Black,
            fontWeight= FontWeight.ExtraBold
        )
    }

}

@Composable
fun CustomTextField(isValueOverMax: Boolean, currentAmount: String, onBackspaceClick: () -> Unit) {

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
                    .width(60.dp)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${currentAmount.filter { it.isDigit() }}",                        fontSize = 45.sp,
                        color = Color.Black,
                        textAlign = TextAlign.End,
                        modifier = Modifier.wrapContentWidth(),
                        style = androidx.compose.ui.text.TextStyle(fontStyle = FontStyle.Normal)
                    )
                    Text(
                        text = "|",
                        fontSize = 45.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .wrapContentWidth()
                            .blink(),
                        style = androidx.compose.ui.text.TextStyle(fontStyle = FontStyle.Normal)


                    )

                    Text(
                        text = "kr.",
                        fontSize = 45.sp,
                        color = Color.Black,
                        textAlign = TextAlign.End,
                        modifier = Modifier.wrapContentWidth(),
                        style = androidx.compose.ui.text.TextStyle(fontStyle = FontStyle.Normal)

                    )

                }
            }
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (currentAmount.isNotEmpty()){
                    IconButton(
                        onClick = onBackspaceClick,

                        //no ripple effect
                        interactionSource = NoRippleInteractionSource()
                    ) {

                        Icon(painter = painterResource(id = R.drawable.baseline_backspace_24), contentDescription = "Delete",modifier=Modifier.padding(end = 16.dp))
                    }
                }
            }
        }
    }
}

//class for no ripple effect to buttons
class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {}
    override fun tryEmit(interaction: Interaction) = true
}

@Composable
fun Modifier.blink(): Modifier {
    var visible by remember { mutableStateOf(true) }
    LaunchedEffect(key1 = Unit) {
        while (true) {
            withFrameNanos {
                visible = !visible
            }
            delay(530L) // Adjust the delay to speed up/slow down the blinking rate
        }
    }
    return then(if (visible) Modifier else Modifier.alpha(0f))
}






@Composable
@Preview
fun BuyScreenPreview() {
    val navController = rememberNavController()
    BuyScreen1(navController)
}