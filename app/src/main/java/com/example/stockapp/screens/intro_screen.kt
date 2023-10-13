package com.example.stockapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.stockapp.R
import com.example.stockapp.Screen

@Composable
fun IntroScreen(navController: NavController)
{
    //val appUiState by appViewModel.uiState.collectAsState()
    var buttonClicked by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize())
    {
        if(buttonClicked)
        {
            navController.navigate(Screen.LoginScreen.route)
        }
        else {
            IntroLayout(onButtonClick = {buttonClicked = true})
        }
    }

}
@Composable
fun IntroLayout(onButtonClick: () -> Unit)
{
   Column(modifier = Modifier.fillMaxSize()) {
       Row() {
           Image(painter = painterResource(id = R.drawable.rocket), contentDescription = "Rocket")
           
           Text(text = "StockZen")
       }
       Text(text = "Market Wisdom at your\nFingertips: Tune into Success")
       
       Button(onClick = {onButtonClick},shape = RoundedCornerShape(50),colors = ButtonDefaults.buttonColors(Color.Blue)) {
           Text(text = "Get Started!")
           
       }
   }
}

@Preview
@Composable
fun IntroLayout() {
    IntroLayout()
}

