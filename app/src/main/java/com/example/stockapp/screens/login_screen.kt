package com.example.stockapp.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen
import com.example.stockapp.ui.theme.Purple40
import com.example.stockapp.viewModels.UserViewModel

@Composable
fun LoginScreen(navController: NavController, userViewModel: UserViewModel)
{

    Box(modifier = Modifier.fillMaxSize())
    {
        LoginLayout(navController = navController, userViewModel = userViewModel)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginLayout(navController: NavController, userViewModel: UserViewModel)
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
                .background(color = Purple40)
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
            userViewModel.login(email,password)
            if (userViewModel.state.value.isLoggedIn) {
                    navController.navigate(Screen.PortfolioScreen.route)
            }
            else { /*TODO: Show error message*/
            }
         })
        {
            Text(text = "Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController(), userViewModel = UserViewModel())
}