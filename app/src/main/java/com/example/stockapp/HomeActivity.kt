package com.example.stockapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockapp.ui.theme.StockAppTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockapp.MainActivity
import com.example.stockapp.authentication.EmailAuthManager
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomTextField
import com.example.stockapp.ui.theme.CustomButton



class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StockAppTheme {

                HomeScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
Column(){
    Image(
        painter = painterResource(id = R.drawable.mikkel),
        contentDescription = "Example Image"
    )
}}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    StockAppTheme {
        HomeScreen()
    }
}