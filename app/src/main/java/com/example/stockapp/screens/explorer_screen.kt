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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen
import androidx.compose.ui.res.stringResource
import com.example.stockapp.R

@Composable
fun ExplorerScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        searchBar(navController)
        ExplorerContent(navController)
    }

}

@Composable
fun searchBar(navController: NavController) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(Color.LightGray)
            .fillMaxWidth()
            .height(52.dp)
            .clickable { navController.navigate(Screen.SearchScreen.route) },
    ) {
        Row(modifier = Modifier
            .align(Alignment.CenterStart)
            .padding(start = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            Icon(imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon_desc),
                modifier = Modifier.size(26.dp)
            )
            Text(
                text = "Search...",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ExplorerContent(navController: NavController) {
    Box(modifier = Modifier
        .fillMaxWidth()
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)) {
            item {
                repeat(10) {
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text(text = "Something here")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            repeat((1..3).random()) {
                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .background(Color.LightGray)
                                        .clickable { }
                                ) {

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewExplorerScreen() {
    ExplorerScreen(navController = rememberNavController())
}