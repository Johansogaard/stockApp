package com.example.stockapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.stockapp.CreateAccountActivity
import com.example.stockapp.LoginActivity
import com.example.stockapp.RegisterActivity
import com.example.stockapp.StartActivity
import com.example.stockapp.ui.theme.StockAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Button(onClick = {
                            val intent = Intent(this@MainActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Go to Login")
                        }
                        Spacer(modifier = Modifier.height(16.dp)) // To give space between elements

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Go to Register")
                        }
                        Spacer(modifier = Modifier.height(16.dp)) // To give space between elements

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, StartActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Go to Start")
                        }
                        Spacer(modifier = Modifier.height(16.dp)) // To give space between elements

                        Button(onClick = {
                            val intent = Intent(this@MainActivity,  CreateAccountActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Go to Create Account")
                        }
                        Button(onClick = {
                            val intent = Intent(this@MainActivity,  HomeActivity::class.java)
                            startActivity(intent)
                        }) {
                            Text("Go to Home")
                        }
                    }
                }
            }
        }
    }
}
object Route{
    const val introScreen = "IntroScreen"
    const val loginScreen = "LoginScreen"
}


