package com.example.stockapp.screens

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.authentication.EmailAuthManager
import com.example.stockapp.data.Screen
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomButton
import com.example.stockapp.ui.theme.CustomTextField
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
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {

        CustomTextField(
            value = username,
            label = "Enter Email or Username"
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = password,
            label = "Enter your password",
            isPassword = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        ClickableText(
            normalText = "Forgot your ",
            clickableText = "Password?",
            onClick = {
                /*do something*/
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(onClick = {
            navController.navigate(Screen.PortfolioScreen.route)
           /* EmailAuthManager.signIn(username.value.text, password.value.text) { isSuccess, errorMessage ->
                if (userViewModel.state.value.isLoggedIn) {
                    navController.navigate(Screen.PortfolioScreen.route)
                }
                else { /*TODO: Show error message*/
                    Toast.makeText(context,"Login failed: $errorMessage",Toast.LENGTH_SHORT).show()
                    println("Login failed. Error message: $errorMessage")
                }
            }*/
        }, text = "Login")
        OrDivider()


        CustomButton(onClick = {
            navController.navigate(Screen.PortfolioScreen.withArgs(username.value.text))
        }, text = "Continue with Google", outlined = true)

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(onClick = {  navController.navigate(Screen.PortfolioScreen.withArgs(username.value.text)) }
            , text = "Continue with Apple", outlined = true)

    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.BottomCenter)
                .fillMaxWidth()
                .offset(y = (-32).dp)
        ) {

            ClickableText(
                normalText = "Dont have an account? ",
                clickableText = "Sign up",
                onClick = {navController.navigate(Screen.ChoseSignupScreen.route)}
            )
            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(
                normalText = "By signing up, you agree to the ",
                clickableText = "Terms and Conditions",
                onClick = {
                    /*do something*/
                }
            )
        }
    }
    }
@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(navController = rememberNavController(), userViewModel = UserViewModel())
}