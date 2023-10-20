package com.example.stockapp

import AuthenticationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import com.example.stockapp.ui.theme.StockAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {


                MainNavHost()
            }
        }

    }

}
object Route{
    const val introScreen = "IntroScreen"
    const val loginScreen = "LoginScreen"
}


