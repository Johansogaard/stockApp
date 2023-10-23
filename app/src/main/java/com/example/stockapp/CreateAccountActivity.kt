package com.example.stockapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.stockapp.MainActivity
import com.example.stockapp.authentication.EmailAuthManager
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomButton
import com.example.stockapp.ui.theme.CustomTextField


class CreateAccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {

                CreateAccountScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen() {


    val context = LocalContext.current

    val fullName = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val referralCode = remember { mutableStateOf(TextFieldValue()) }

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
            text = "Get started",
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
            CustomButton(onClick = {

                EmailAuthManager.signUp(email.value.text, password.value.text, username.value.text) {
                        isSuccess, errorMessage ->
                    if (isSuccess)
                    {
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                        println("successfull")
                    }
                    else{
                        Toast.makeText(context,"Sign up failed. Error message: $errorMessage", Toast.LENGTH_SHORT).show()
                        println("Sign up failed. Error message: $errorMessage")
                    }
                }

            }, text = "Sign Up")
            Spacer(modifier = Modifier.height(16.dp))

            ClickableText(
                normalText = "Already have an account? ",
                clickableText = "Log in",
                onClick = {
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }

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
fun CreateAccountScreenPreview() {
    StockAppTheme {
        LoginScreen()
    }
}