package com.example.stockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           MainNavHost()
        }

    }

}
object Route{
    const val introScreen = "IntroScreen"
    const val loginScreen = "LoginScreen"
}


