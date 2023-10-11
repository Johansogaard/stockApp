package com.example.stockapp.screens



import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockapp.Screen

@Composable
fun LoginScreen(navController: NavController)
{
  // val appUiState by appViewModel.uiState.collectAsState()

   Box(modifier = Modifier.fillMaxSize())
   {
      LoginLayOut(navController)
   }

}

@Composable
fun LoginStatus()
{

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginLayOut(navController: NavController)
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
      Spacer(modifier = Modifier.height(8.dp))
      Button(
         onClick = {
         navController.navigate(Screen.PortfolioScreen.withArgs(textValue))
         },
         modifier = Modifier.align(Alignment.End)
      ) {
         Text(text = "Sign UP")
      }
   }
}

