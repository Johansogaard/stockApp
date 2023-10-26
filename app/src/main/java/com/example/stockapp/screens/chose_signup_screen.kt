package com.example.stockapp.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen

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
   var textValue by remember {mutableStateOf("") }
   Column(modifier = Modifier
      .fillMaxSize()
      .padding(14.dp))
   {
      Text(text = "Get Started")

      OutlinedTextField(
         value =textValue ,
         onValueChange ={textValue = it},
         modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp),
         label={Text("Email")} )

      Button(
         onClick = {
         navController.navigate(Screen.SignUpScreen.route)
         },
         modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
      ) {
         Text(text = "Create Account")
      }
      OrDivider()

      Button(
         onClick = {
            navController.navigate(Screen.PortfolioScreen.withArgs(textValue))
         },
         modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
      ) {
         Text(text = "Continue with Play Store")
      }

      Button(
         onClick = {
            navController.navigate(Screen.PortfolioScreen.withArgs(textValue))
         },
         modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp)
      ) {
         Text(text = "Continue with Google")
      }
      Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
         Text(text = "Already have an account?",modifier = Modifier.align(Alignment.Bottom))
         TextButton(onClick = { navController.navigate(Screen.LoginScreen.route) },modifier = Modifier.align(Alignment.Bottom)) {
            Text(text = "Log in", color = Color.Blue)
      }
  
         
         
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