package com.example.stockapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.data.Screen
import com.example.stockapp.screens.ChoseSignupScreen
import com.example.stockapp.screens.ExplorerScreen
import com.example.stockapp.screens.IntroScreen
import com.example.stockapp.screens.LoginScreen
import com.example.stockapp.screens.PortfolioScreen
import com.example.stockapp.screens.SearchScreen
import com.example.stockapp.screens.SignUpScreen
import com.example.stockapp.ui.NavigationBar
import com.example.stockapp.viewModels.CompetitionViewModel
import com.example.stockapp.viewModels.LayoutViewModel
import com.example.stockapp.viewModels.StockViewModel
import com.example.stockapp.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost(
    userViewModel: UserViewModel,
    stocksViewModel: StockViewModel,
    competitionViewModel: CompetitionViewModel,
    layoutViewModel: LayoutViewModel
) {

    layoutViewModel.state.value.showNavigationBar = true

    val navController = rememberNavController();
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
            if (layoutViewModel.state.value.showNavigationBar) {
                NavigationBar(navController = navController)
            }
        },
        content = { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = startDestination)
                {
                    composable(route = Screen.IntroScreen.route) {
                        IntroScreen(navController = navController)
                        layoutViewModel.showNavigationBar(false)
                    }
                    composable(route = Screen.ChoseSignupScreen.route) {
                        Column {
                            ChoseSignupScreen(navController = navController)
                            layoutViewModel.showNavigationBar(false)
                        }
                    }
                    composable(route = Screen.PortfolioScreen.route) {
                        PortfolioScreen(navController = navController)
                        layoutViewModel.showNavigationBar(true)
                    }
                    composable(route = Screen.SignUpScreen.route) {
                        SignUpScreen(navController = navController)
                        layoutViewModel.showNavigationBar(false)
                    }
                    composable(route = Screen.LoginScreen.route) {
                        LoginScreen(navController = navController, userViewModel = userViewModel)
                        layoutViewModel.showNavigationBar(false)
                    }
                    composable(route = Screen.ExplorerScreen.route) {
                        ExplorerScreen(navController = navController)
                        layoutViewModel.showNavigationBar(true)
                    }
                    composable(route = Screen.SearchScreen.route) {
                        SearchScreen(navController = navController)
                        layoutViewModel.showNavigationBar(false)
                    }

                }
            }
        }
    )
}