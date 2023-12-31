package com.example.stockapp.mvvm.start.intro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.mvvm.Screen
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomButton

@Composable
fun IntroScreen(navController: NavController)
{
    //val appUiState by appViewModel.uiState.collectAsState()

    val introViewModel: IntroViewModel = hiltViewModel()

    Box(modifier = Modifier.fillMaxSize())
    {
        IntroLayout(navController = navController)
    }
}
@Composable
fun IntroLayout(navController: NavController)
{

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val textArray = stringArrayResource(R.array.intro_text)
        for (text in textArray) {
            BeginText(text)
        }
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
            CustomButton( onClick = {navController.navigate(Screen.ChoseSignupScreen.route)}, text = "Get Started")
            Spacer(modifier = Modifier.height(16.dp))
            ClickableText(
                normalText = stringResource(R.string.intro_have_account_q),
                clickableText = stringResource(R.string.common_log_in),
                onClick = { navController.navigate(Screen.LoginScreen.route) }
            )
       }
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
    Spacer(modifier = Modifier.height(8.dp))
}
@Preview(showBackground = true)
@Composable
fun PreviewIntroScreen() {
    IntroScreen(navController = rememberNavController())
}

