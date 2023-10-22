package com.example.stockapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockapp.Screen
import com.example.stockapp.authentication.EmailAuthManager

@Composable
fun PortfolioScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize())
    {
        PortfolioLayout(navController)

    }

}
@Composable
fun PortfolioLayout(navController: NavController)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(14.dp)
    )
    {
        Row( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, // Align children to the start and end of the Row
            verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Overview")
            IconButton(
                onClick = {
                          EmailAuthManager.signOut()
                    navController.navigate(Screen.IntroScreen.route)
                },


            ) {
                Icon(
                    imageVector = Icons.Rounded.ExitToApp,
                    contentDescription = "exit app icon",
                    tint = Color.Black // You can change the icon color here
                )
            }
        }
        }
       

    }
