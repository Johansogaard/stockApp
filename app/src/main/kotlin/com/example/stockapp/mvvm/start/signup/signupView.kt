package com.example.stockapp.mvvm.start.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.mvvm.Screen
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
            text = stringResource(R.string.signup_get_started),
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
            label = stringResource(R.string.signup_name)
        )

        CustomTextField(
            value = email,
            label = stringResource(R.string.common_email)
        )

        CustomTextField(
            value = username,
            label = stringResource(R.string.signup_username)
        )

        CustomTextField(
            value = password,
            label = stringResource(R.string.signup_password)
        )

        CustomTextField(
            value = referralCode,
            label = stringResource(R.string.signup_referral_opt)
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Signup Button
        CustomButton(
            onClick = {

                    /*EmailAuthManager.signUp(email.value.text, password.value.text, username.value.text) {
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
*/


            },
            text = stringResource(R.string.signup_sign_up),
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .offset(y = (-32).dp)
        ) {

            ClickableText(
                normalText = stringResource(R.string.signup_have_account_q),
                clickableText = stringResource(R.string.common_log_in),
                onClick = {navController.navigate(Screen.LoginScreen.route)}
            )
            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(
                normalText = stringResource(R.string.signup_terms_text),
                clickableText = stringResource(R.string.signup_terms_link),
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