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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.stockapp.MainActivity
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomButton


class StartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {

                StartScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // First Section: Button at the top
        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Go to Main")
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Second Section: Logo and Texts
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = "logo",
                style = TextStyle(fontSize = 24.sp)
            )

            val space = 8.dp
            Spacer(modifier = Modifier.height(space))
            BeginText("Market Wisdom")
            Spacer(modifier = Modifier.height(space))
            BeginText("at Your")
            Spacer(modifier = Modifier.height(space))
            BeginText("Fingertips:")
            Spacer(modifier = Modifier.height(space))
            BeginText("Tune into")
            Spacer(modifier = Modifier.height(space))
            BeginText("Success.")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Third Section: Login Button and Clickable Text
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.BottomCenter) // this positions the Column at the bottom of the Box
                    .fillMaxWidth()
                    .offset(y = (-32).dp)
            ) {

                CustomButton( onClick = {
                    val intent = Intent(context, RegisterActivity::class.java)
                    context.startActivity(intent)
                }, text = "Get Started")


                Spacer(modifier = Modifier.height(16.dp))

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
}

@Preview(showBackground = true)
@Composable
fun StartScreenPreview() {
    StockAppTheme {
        StartScreen()
    }
}
@Composable
fun BeginText(text: String) {
    Text(
        text = "$text",
        style = TextStyle(
            fontSize = 42.sp,
            fontWeight = FontWeight(800)
            )
    )
}