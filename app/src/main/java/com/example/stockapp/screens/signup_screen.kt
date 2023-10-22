package com.example.stockapp.screens

import AuthenticationManager
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockapp.Screen


@Composable
fun SignUpScreen(navController: NavController)
{
    // val appUiState by appViewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize())
    {
        SignUpLayout(navController)
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpLayout(navController: NavController)
{

    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Full Name
        TextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Email
        TextField(
            value = email,
            onValueChange = { email = it.trimStart().trimEnd() },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Username
        TextField(
            value = username,
            onValueChange = { username = it.trimStart().trimEnd() },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Password
        TextField(
            value = password,
            onValueChange = { password = it.trimStart().trimEnd() },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        // Signup Button
        Button(
            onClick = {

                    AuthenticationManager.signUp(email,password,username) {
                          isSuccess, errorMessage ->
                          if (isSuccess)
                          {

                            println("successfull")
                              navController.navigate(Screen.LoginScreen.route)
                          }
                          else{
                              println("Sign up failed. Error message: $errorMessage")
                          }
                      }



            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Sign Up")
        }
    }

}
