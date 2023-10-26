package com.example.stockapp

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
import com.example.stockapp.screens.ChoseSignupScreen
import com.example.stockapp.screens.ExplorerScreen
import com.example.stockapp.screens.IntroScreen
import com.example.stockapp.screens.LoginScreen
import com.example.stockapp.screens.PortfolioScreen
import com.example.stockapp.screens.SearchScreen
import com.example.stockapp.screens.SignUpScreen
import com.example.stockapp.ui.NavigationBar
import com.example.stockapp.viewModels.CompetitionViewModel
import com.example.stockapp.viewModels.CurrentAppViewModel
import com.example.stockapp.viewModels.StockViewModel
import com.example.stockapp.viewModels.UserViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavHost(
    userViewModel: UserViewModel,
    stockViewModel: StockViewModel,
    competitionViewModel: CompetitionViewModel,
    currentAppViewModel: CurrentAppViewModel
) {

    currentAppViewModel.state.value.showNavigationBar = true

    var showNavigate by remember {
        mutableStateOf(true)
    }


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
            if (showNavigate) {
                NavigationBar(navController = navController)
            }
        },
        content = { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                NavHost(navController = navController, startDestination = startDestination)
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
                    composable(route = Screen.ExplorerScreen.route) {
                        ExplorerScreen(navController = navController)
                        showNavigate = true
                    }
                    composable(
                        route = Screen.SearchScreen.route,
                        enterTransition = {
                            null
                        },
                        exitTransition = {
                            null
                        }
                    ) {
                        SearchScreen(navController = navController)
                        showNavigate = false
                    }
                }
            }
        }
    )
}