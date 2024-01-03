package com.example.stockapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.mvvm.screens.Screen
import com.example.stockapp.ui.NavigationBar
import com.example.stockapp.mvvm.viewModels.CompetitionViewModel
import com.example.stockapp.mvvm.viewModels.CurrentAppViewModel
import com.example.stockapp.mvvm.viewModels.StocksViewModel
import com.example.stockapp.mvvm.viewModels.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.stockapp.integration.api.Authentication
import com.example.stockapp.mvvm.screens.ChoseSignupScreen
import com.example.stockapp.mvvm.screens.ExplorerScreen
import com.example.stockapp.mvvm.screens.IndexScreen
import com.example.stockapp.mvvm.screens.IntroScreen
import com.example.stockapp.mvvm.screens.OrderScreen
import com.example.stockapp.mvvm.screens.PortfolioScreen
import com.example.stockapp.mvvm.screens.SearchScreen
import com.example.stockapp.mvvm.screens.SignUpScreen
import com.example.stockapp.mvvm.screens.StockViewScreen
import com.example.stockapp.mvvm.screens.TransactionScreen
import com.example.stockapp.mvvm.screens.WatchScreen
import com.example.stockapp.screens.*
import com.example.stockapp.mvvm.viewModels.BuyViewModel

@SuppressLint("RestrictedApi")
@Composable
fun MainNavHost(
    authentication: Authentication,
    userViewModel: UserViewModel,
    stocksViewModel: StocksViewModel,
    buyViewModel: BuyViewModel,
    competitionViewModel: CompetitionViewModel,
    currentAppViewModel: CurrentAppViewModel
) {

    val navController = rememberNavController();

    var showNavigate by remember {
        mutableStateOf(true)
    }

    val loggedInUser = authentication.state.collectAsState().value.currentUser
    val userCreated = authentication.state.collectAsState().value.userCreated
    LaunchedEffect(loggedInUser) {
        Log.i("FirebaseAuth", "User log in status: $loggedInUser")
    }

    var startDestination: Screen

    if (loggedInUser == null) {
        startDestination = Screen.IntroScreen
    }
    else {
        if (userCreated == false) {
            startDestination = Screen.SignUpScreen
        }
        else {
            startDestination = Screen.PortfolioScreen
        }
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
                    startDestination = startDestination.route,
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
                        BuyScreen1(navController = navController, buyViewModel = buyViewModel)
                        showNavigate = false
                    }
                    composable(route = Screen.BuyScreen2.route) {
                        BuyScreen2(navController = navController,buyViewModel= buyViewModel)
                        showNavigate = false
                    }
                    composable(route = Screen.BuyScreen3.route) {
                        BuyScreen3(navController = navController,buyViewModel= buyViewModel)
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
                    composable(route = "StockViewScreen/{stockSymbol}") { backStackEntry ->
                        StockViewScreen(
                            navController = navController,
                            stocksViewModel = stocksViewModel,
                            stockSymbol = backStackEntry.arguments?.getString("stockSymbol") ?: "NOVO"
                        )
                        showNavigate = true
                    }
                }
            }
        }
    )
}