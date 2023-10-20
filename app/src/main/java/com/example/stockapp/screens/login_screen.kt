package com.example.stockapp.screens

import AuthenticationManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockapp.Screen

@Composable
fun LoginScreen(navController: NavController, authManager: AuthenticationManager)
{
    //val appUiState by appViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize())
    {
        LoginLayout(navController = navController,authManager = AuthenticationManager())
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginLayout(navController: NavController, authManager: AuthenticationManager)
{
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxWidth()) {
        // Email
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        // Password
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Button(onClick = {
            authManager.signIn(email,password)
            {
                    isSuccess, errorMessage ->
                if (isSuccess)
                {
                    println("successfull")
                    navController.navigate(Screen.PortfolioScreen.route)
                }
                else{
                    println("Login failed. Error message: $errorMessage")
                }
            }
         })
        {
            Text(text = "Login")
        }
    }
}