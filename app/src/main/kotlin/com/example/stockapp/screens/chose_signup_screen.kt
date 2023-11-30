package com.example.stockapp.screens



import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import com.example.stockapp.R

import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen
import com.example.stockapp.ui.theme.ClickableText
import com.example.stockapp.ui.theme.CustomButton
import com.example.stockapp.ui.theme.CustomTextField

@Composable
fun ChoseSignupScreen(navController: NavController)
{
  // val appUiState by appViewModel.uiState.collectAsState()

   Box(modifier = Modifier.fillMaxSize())
   {
      ChoseSignupLayOut(navController)
   }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoseSignupLayOut(navController: NavController)
{
   val email = remember { mutableStateOf(TextFieldValue()) }
   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(16.dp),
      horizontalAlignment = Alignment.CenterHorizontally
   )
   {
      Text(text = stringResource(R.string.choose_signup_get_started))

      CustomTextField(
         value = email,
         label = stringResource(R.string.common_email)
      )
      Spacer(modifier = Modifier.height(16.dp))

      CustomButton(onClick = {
         navController.navigate(Screen.SignUpScreen.route)
      }, text = stringResource(R.string.choose_signup_create_account))

      OrDivider()


      CustomButton(onClick = {
         navController.navigate(Screen.PortfolioScreen.withArgs(email.value.text))
      }, text = stringResource(R.string.common_with_google), outlined = true)

      Spacer(modifier = Modifier.height(16.dp))

      CustomButton(onClick = {
         navController.navigate(Screen.PortfolioScreen.withArgs(email.value.text)) },
         text = stringResource(R.string.common_with_apple),
         outlined = true)
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
            normalText = stringResource(R.string.choose_signup_have_account),
            clickableText = stringResource(R.string.common_login),
            onClick = {navController.navigate(Screen.LoginScreen.route)}
         )
         Spacer(modifier = Modifier.height(16.dp))

         ClickableText(
            normalText = stringResource(R.string.choose_signup_terms_text),
            clickableText = stringResource(R.string.choose_signup_terms_link_text),
            onClick = {
               /*do something*/
            }
         )
      }
   }
}
@Composable
fun OrDivider() {
   val backgroundColor: Color = MaterialTheme.colorScheme.background
   Box(
      modifier = Modifier
         .fillMaxWidth()
         .padding(horizontal = 16.dp)
         .height(50.dp),
      contentAlignment = Alignment.Center
   ) {
      Divider(
         color = Color.Black,
         modifier = Modifier.fillMaxWidth(1f)
      )
       Text(
         text = "or",
         modifier = Modifier
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp)
      )

   }
}

@Preview(showBackground = true)
@Composable
fun PreviewChoseSignupScreen() {
   ChoseSignupScreen(navController = rememberNavController())
}