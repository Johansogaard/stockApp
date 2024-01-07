package com.example.stockapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.integration.firebase.authentication.Authentication
import com.example.stockapp.mvvm.Screen
import com.example.stockapp.mvvm.buy.buy.BuyScreen1
import com.example.stockapp.mvvm.buy.buy.BuyScreen2
import com.example.stockapp.mvvm.buy.buy.BuyScreen3
import com.example.stockapp.mvvm.buy.buy.BuyViewModel
import com.example.stockapp.mvvm.buy.transaction.TransactionScreen
import com.example.stockapp.mvvm.competition.CompetitionViewModel
import com.example.stockapp.mvvm.index.IndexScreen
import com.example.stockapp.mvvm.order.OrderScreen
import com.example.stockapp.mvvm.portfolio.PortfolioScreen
import com.example.stockapp.mvvm.search.explorer.ExplorerScreen
import com.example.stockapp.mvvm.search.search.SearchScreen
import com.example.stockapp.mvvm.start.intro.IntroScreen
import com.example.stockapp.mvvm.start.login.LoginViewModel
import com.example.stockapp.mvvm.start.signup.ChoseSignupScreen
import com.example.stockapp.mvvm.start.signup.SignUpScreen
import com.example.stockapp.mvvm.start.signup.SignupViewModel
import com.example.stockapp.mvvm.stock.StockViewModel
import com.example.stockapp.mvvm.stock.StockViewScreen
import com.example.stockapp.mvvm.watch.WatchScreen
import com.example.stockapp.screens.*
import com.example.stockapp.ui.NavigationBar

@SuppressLint("RestrictedApi")
@Composable
fun MainNavHost(
    authentication: Authentication,
    loginViewModel: LoginViewModel,
    stockViewModel: StockViewModel,
    buyViewModel: BuyViewModel,
    signupViewModel: SignupViewModel,
    competitionViewModel: CompetitionViewModel,

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
                            ChoseSignupScreen(navController = navController,signupViewModel =signupViewModel)
                            showNavigate = false
                        }
                    }
                    composable(route = Screen.PortfolioScreen.route) {
                        PortfolioScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(route = Screen.SignUpScreen.route) {
                        SignUpScreen(navController = navController, signupViewModel = signupViewModel )
                        showNavigate = false
                    }
                    composable(route = Screen.LoginScreen.route) {
                        LoginScreen(navController = navController, loginViewModel = loginViewModel)
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
                            stockViewModel = stockViewModel,
                            stockSymbol = backStackEntry.arguments?.getString("stockSymbol") ?: "NOVO"
                        )
                        showNavigate = true
                    }
                    composable(route = Screen.GoogleSignInView.route) {
                        LoginGoogleView(loginViewModel = loginViewModel,navController = navController)
                        showNavigate = false
                    }
                }
            }
        }
    )
}