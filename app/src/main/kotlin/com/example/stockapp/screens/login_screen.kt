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
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
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
import com.example.stockapp.R

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
    val loginFailedMessage = stringResource(R.string.login_failed)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )  {

        CustomTextField(
            value = username,
            label = stringResource(R.string.login_name_email),
        )
        Spacer(modifier = Modifier.height(16.dp))

        CustomTextField(
            value = password,
            label = stringResource(R.string.login_enter_password),
            isPassword = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        ClickableText(
            normalText = stringArrayResource(R.array.login_forgot)[0],
            clickableText = stringArrayResource(R.array.login_forgot)[1],
            onClick = {
                /*do something*/
            }
        )

        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(onClick = {                   // navController.navigate(Screen.PortfolioScreen.route)

            EmailAuthManager.signIn(username.value.text, password.value.text) { isSuccess, errorMessage ->
                if (userViewModel.state.value.isLoggedIn) {
                    navController.navigate(Screen.PortfolioScreen.route)
                }
                else {
                    val formattedErrorMessage = String.format(loginFailedMessage, errorMessage)
                    Toast.makeText(context, formattedErrorMessage, Toast.LENGTH_SHORT).show()
                    println("Login failed. Error message: $errorMessage")
                }
            }
        }, text = stringResource(R.string.common_login))
        OrDivider()


        CustomButton(onClick = {
            navController.navigate(Screen.PortfolioScreen.withArgs(username.value.text))
        }, text = stringResource(R.string.common_with_google), outlined = true)

        Spacer(modifier = Modifier.height(16.dp))

        CustomButton(onClick = {  navController.navigate(Screen.PortfolioScreen.withArgs(username.value.text)) }
            , text = stringResource(R.string.common_with_apple), outlined = true)

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
                normalText = stringResource(R.string.login_no_account_q),
                clickableText = stringResource(R.string.login_sign_up),
                onClick = {navController.navigate(Screen.ChoseSignupScreen.route)}
            )
            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(
                normalText = stringResource(R.string.login_terms_text),
                clickableText = stringResource(R.string.login_terms_link_text),
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