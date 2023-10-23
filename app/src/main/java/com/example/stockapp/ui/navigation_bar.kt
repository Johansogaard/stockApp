package com.example.stockapp.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun NavigationBar(navController: NavController) {
    BottomAppBar(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth()
            .background(Color.LightGray),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column() {
                IconButton(onClick = {  }) {
                    Icon(Icons.Default.Home, contentDescription = null)
                }
                Text(text = "Portfolio")
            }
            Column() {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Menu, contentDescription = null)
                }
                Text(text = "Orders")
            }
            Column() {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Search, contentDescription = null)
                }
                Text(text = "Explore")
            }
            Column() {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null)
                }
                Text(text = "Market")
            }
            Column() {
                IconButton(onClick = { }) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
                Text(text = "Watch list")
            }
        }
    }

}