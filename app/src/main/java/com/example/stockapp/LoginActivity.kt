package com.example.stockapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stockapp.ui.theme.StockAppTheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.stockapp.MainActivity
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomTextField
import com.example.stockapp.ui.theme.CustomButton



class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {

                LoginScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {


    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First Section: Button at the top
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }
        ) {
            Text("Go to Main")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Logo
        Text(
            text = "logo",
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight(800)
            )
        )


        Text(
            text = "Log in",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(700)
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        // This column contains other items and is centered within the Box
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            val username = remember { mutableStateOf(TextFieldValue()) }
            val password = remember { mutableStateOf(TextFieldValue()) }

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
            CustomButton(onClick = { /* login*/ }, text = "Login")
            Spacer(modifier = Modifier.height(16.dp))



            Text(
                text = "---------------------------------- or ----------------------------------",

            )
            Spacer(modifier = Modifier.height(16.dp))


            CustomButton(onClick = { /* login*/ }, text = "Continue with Google", outlined = true)
            Spacer(modifier = Modifier.height(16.dp))


            CustomButton(onClick = { /* login*/ }, text = "Continue with Apple", outlined = true)



        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.BottomCenter) // this positions the Column at the bottom of the Box
                .fillMaxWidth()
                .offset(y = (-32).dp)
        ) {

            ClickableText(
                normalText = "Already have an account? ",
                clickableText = "Log in",
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    StockAppTheme {
        LoginScreen()
    }
}