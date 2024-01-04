package com.example.stockapp.mvvm.search.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.R
import com.example.stockapp.stockApi.ShowStockists
import com.example.stockapp.stockApi.searchStocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
// skal den være her


@Composable
fun SearchScreen(navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<String>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchableBar(searchQuery) { query ->
            searchQuery = query
            coroutineScope.launch(Dispatchers.IO) {
                val results = searchStocks(query)
                withContext(Dispatchers.Main) {
                    searchResults = results
                    println("Search Results: $results") // Debug log
                }
            }
        }

        if (searchResults.isNotEmpty()) {
            ShowStockists(navController, searchResults)
        } else {
            Text("No results found or search not yet performed.")
        }
    }
}

@Composable
fun SearchableBar(query: String, onQueryChanged: (String) -> Unit) {
    var text by remember { mutableStateOf(query) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            onQueryChanged(it)
        },
        label = { Text("Search Stocks") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun LatestSearches() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
    ) {
        item {
            repeat(30) {
                Divider(color = Color.LightGray, thickness = 1.dp)
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column() {
                        Text(
                            text = "Something here",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Something here too",
                            fontSize = 12.sp,
                            color = Color.LightGray
                        )
                    }
                    Box(modifier = Modifier
                        .align(Alignment.CenterEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.search_remove_desc),
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