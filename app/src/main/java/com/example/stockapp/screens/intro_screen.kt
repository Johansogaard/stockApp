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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.stockapp.R
import com.example.stockapp.Screen

@Composable
fun IntroScreen(navController: NavController)
{
    //val appUiState by appViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize())
    {
        IntroLayout(navController)
    }

}
@Composable
fun IntroLayout(navController: NavController)
{
   Column(modifier = Modifier.fillMaxSize()) {
       Row() {
           Image(painter = painterResource(id = R.drawable.rocket), contentDescription = "Rocket")
           
           Text(text = "StockZen")
       }
       Text(text = "Market Wisdom at your\nFingertips: Tune into Success")
       
       Button(onClick = { navController.navigate(Screen.LoginScreen.route)},shape = RoundedCornerShape(50),colors = ButtonDefaults.buttonColors(Color.Blue)) {
           Text(text = "Get Started!")
           
       }
   }
}