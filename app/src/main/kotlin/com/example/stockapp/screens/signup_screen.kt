package com.example.stockapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen
import com.example.stockapp.authentication.EmailAuthManager
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomButton
import com.example.stockapp.ui.theme.CustomTextField


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
    val fullName = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val referralCode = remember { mutableStateOf(TextFieldValue()) }
    val snackbarHostState = remember { SnackbarHostState() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Get started",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(700)
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )
        // Full Name
        CustomTextField(
            value = fullName,
            label = "Full name"
        )

        CustomTextField(
            value = email,
            label = "Email"
        )

        CustomTextField(
            value = username,
            label = "Create a Username"
        )

        CustomTextField(
            value = password,
            label = "Create a Password"
        )

        CustomTextField(
            value = referralCode,
            label = "Referral Code (Optional)"
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Signup Button
        CustomButton(
            onClick = {

                    EmailAuthManager.signUp(email.value.text, password.value.text, username.value.text) {
                          isSuccess, errorMessage ->
                          if (isSuccess)
                          {

                            println("successfull")
                              navController.navigate(Screen.LoginScreen.route)
                          }
                          else{
                              Toast.makeText(context,"Sign up failed. Error message: $errorMessage",Toast.LENGTH_SHORT).show()
                              println("Sign up failed. Error message: $errorMessage")
                          }
                      }



            },
            text = "Sign Up"
        )
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
                normalText = "Already have an account?",
                clickableText = " Login",
                onClick = {navController.navigate(Screen.LoginScreen.route)}
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

@Preview (showBackground = true)
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(navController = rememberNavController())
}