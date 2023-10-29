package com.example.stockapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.Screen
import com.example.stockapp.authentication.EmailAuthManager
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun BuyScreen3(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)

    )
    {
        BuyScreen3Layout(navController)

    }

}
@Composable
fun BuyScreen3Layout(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
Column( modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally) {

    Spacer(modifier = Modifier.weight(1f))

    Row(
        Modifier
            .fillMaxWidth()
            .height(90.dp),horizontalArrangement=Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.dk),
            contentDescription = "stringResource(id = R.string.dog_content_description)"
        )

    }
    Spacer(modifier = Modifier.size(10.dp))

    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.Center){
Text(text = "OMX Copenhagen 25", fontSize = 28.sp, fontWeight = FontWeight.Bold)
}
    Spacer(modifier = Modifier.size(2.dp))
    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.Center){
        Text(text = "OMXC25", fontSize = 12.sp, fontWeight = FontWeight.Normal)
    }
    Spacer(modifier = Modifier.size(18.dp))

    Divider(
        color = Color.LightGray,
        modifier = Modifier.width(320.dp)
    )

    Row(
        Modifier
            .fillMaxWidth()
            .height(200.dp),horizontalArrangement=Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.fireworks),
            contentDescription = "stringResource(id = R.string.dog_content_description)"
        )

    }
    Spacer(modifier = Modifier.size(10.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.Center) {
        Text(text = "DKK 10.000", fontSize = 48.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF1A65E7))
    }
    Spacer(modifier = Modifier.size(30.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.Center) {
        Text(text = "Buy Order Received!", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
    Spacer(modifier = Modifier.size(10.dp))

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement=Arrangement.Center) {
    Text(text = "Your order has been received and will be\n" +
            "executed as soon as the market opens")

}
    Spacer(modifier = Modifier.size(35.dp))

    Button(
        onClick = {
            // Add code to handle the buy action
        },modifier = Modifier
            .height(52.dp)
            .width(320.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1A65E7))

    ) {
        Text(text = "View my portfolio", fontSize = 16.sp)}

    Spacer(modifier = Modifier.size(20.dp))

    Button(

        onClick = {
            // Add code to handle the buy action
        },modifier = Modifier
            .height(52.dp)
            .width(320.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFDDE8FC))

    ) {
        Text(text = "Back to OMXC25 stock", fontSize = 16.sp, color= Color(0xFF1A65E7))}


    Spacer(modifier = Modifier.size(40.dp))

}
    }
}



@Composable
@Preview
fun BuyScreen3Preview() {
    val navController = rememberNavController()
    BuyScreen3(navController)
}