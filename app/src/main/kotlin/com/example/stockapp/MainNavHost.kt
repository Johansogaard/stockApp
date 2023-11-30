package com.example.stockapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen
import com.example.stockapp.ui.NavigationBar
import com.example.stockapp.viewModels.CompetitionViewModel
import com.example.stockapp.viewModels.CurrentAppViewModel
import com.example.stockapp.viewModels.StocksViewModel
import com.example.stockapp.viewModels.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.stockapp.screens.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost(
    userViewModel: UserViewModel,
    stocksViewModel: StocksViewModel,
    competitionViewModel: CompetitionViewModel,
    currentAppViewModel: CurrentAppViewModel
) {

    //currentAppViewModel.state.value.showNavigationBar = true

    var showNavigate by remember {
        mutableStateOf(true)
    }


    val navController = rememberNavController()
    val startDestination = if(userViewModel.state.value.isLoggedIn)
    {
        Screen.PortfolioScreen.route
    }
    else
    {
        Screen.IntroScreen.route
    }

    Scaffold(
        bottomBar = {
            if (showNavigate) {
                NavigationBar(navController = navController)
            }
        },
        content = { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(
                    navController = navController,
                    startDestination = startDestination,
                    //enterTransition = { EnterTransition.None },
                    //exitTransition = { ExitTransition.None },
                    )
                {
                    composable(route = Screen.IntroScreen.route) {
                        IntroScreen(navController = navController)
                        showNavigate = false
                    }
                    composable(route = Screen.ChoseSignupScreen.route) {
                        Column {
                            ChoseSignupScreen(navController = navController)
                            showNavigate = false
                        }
                    }
                    composable(route = Screen.PortfolioScreen.route) {
                        PortfolioScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(route = Screen.SignUpScreen.route) {
                        SignUpScreen(navController = navController)
                        showNavigate = false
                    }
                    composable(route = Screen.LoginScreen.route) {
                        LoginScreen(navController = navController, userViewModel = userViewModel)
                        showNavigate = false
                    }
                    composable(
                        route = Screen.ExplorerScreen.route,) {
                        val searchTerm = remember { mutableStateOf("") }
                        ExplorerScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(
                        route = Screen.SearchScreen.route,
                    ) {
                        SearchScreen(navController = navController)
                        showNavigate = false
                    }
                    composable(route = Screen.BuyScreen1.route) {
                        BuyScreen1(navController = navController)
                        showNavigate = false
                    }
                    composable(route = Screen.BuyScreen2.route) {
                        BuyScreen2(navController = navController)
                        showNavigate = false
                    }
                    composable(route = Screen.BuyScreen3.route) {
                        BuyScreen3(navController = navController)
                        showNavigate = false
                    }
                    composable(route = Screen.IndexScreen.route) {
                        IndexScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(route = Screen.TransactionScreen.route) {
                        TransactionScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(route = Screen.OrderScreen.route) {
                        OrderScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(route = Screen.WatchScreen.route) {
                        WatchScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(route = Screen.StockViewScreen.route) {
                        StockViewScreen(navController = navController, stocksViewModel = stocksViewModel)
                        showNavigate = true
                    }
                }
            }
        }
    )
}