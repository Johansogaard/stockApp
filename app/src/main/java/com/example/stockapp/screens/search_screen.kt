package com.example.stockapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.Screen

@Composable
fun SearchScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        SearchableBar(navController)
        LatestSearches()
    }
}

@Composable
fun SearchableBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .background(Color.Gray)
            .padding(12.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(Color.LightGray)
                .weight(1f)
                .height(30.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier
                .padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                )
                Text(
                    color = Color.White,
                    text = "What would you like to find?",
                )
            }
        }
        Text(modifier = Modifier
            .clickable { navController.navigate(Screen.ExplorerScreen.route) }
            .height(25.dp)
            .align(Alignment.Bottom)
            .padding(horizontal = 10.dp),
            color = Color.White,
            text = "Cancel",
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LatestSearches() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
    ) {
        item {
            repeat(30) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(text = "Something here")
                    Box(modifier = Modifier
                        .align(Alignment.CenterEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Clear,
                            contentDescription = "Search Icon",
                            modifier = Modifier
                                .size(26.dp)
                                .align(Alignment.CenterEnd)
                                .clickable { /*TODO*/ },
                            tint = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewSearchScreen() {
    SearchScreen(navController = rememberNavController())
}