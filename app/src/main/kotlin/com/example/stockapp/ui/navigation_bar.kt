package com.example.stockapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stockapp.mvvm.Screen

@Composable
fun NavigationBar(navController: NavController) {
    BottomAppBar(
        modifier = Modifier
            .background(Color(0xFF3264DE))
            .height(52.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier.clickable(onClick = { navController.navigate(Screen.PortfolioScreen.route) }),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = null,
                )
                Text(
                    text = "Portfolio",
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.clickable(onClick = { navController.navigate(Screen.OrderScreen.route) }),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = null,
                )
                Text(
                    text = "Orders",
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.clickable(
                    onClick = { navController.navigate(Screen.ExplorerScreen.route) }),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null,
                )
                Text(
                    text = "Explorer",
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.clickable(onClick = { navController.navigate(Screen.IndexScreen.route)}),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = null,
                )
                Text(
                    text = "Market",
                    fontSize = 12.sp
                )
            }
            Column(
                modifier = Modifier.clickable(onClick = { navController.navigate(Screen.WatchScreen.route)}),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null,
                )
                Text(
                    text = "Watch list",
                    fontSize = 12.sp
                )
            }
        }
    }

}