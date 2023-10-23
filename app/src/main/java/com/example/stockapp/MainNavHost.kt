package com.example.stockapp

import com.example.stockapp.authentication.EmailAuthManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.example.stockapp.viewModels.CompetitionViewModel
import com.example.stockapp.viewModels.StockViewModel
import com.example.stockapp.viewModels.UserViewModel

@Composable
fun MainNavHost(
    userViewModel: UserViewModel,
    stocksViewModel: StockViewModel,
    competitionViewModel: CompetitionViewModel,
) {
    val navController = rememberNavController();
    val startDestination = if(userViewModel.state.value.isLoggedIn)
    {
        Screen.PortfolioScreen.route
    }
    else
    {
        Screen.IntroScreen.route
    }
    NavHost(navController = navController, startDestination = startDestination)
    {
       composable(route = Screen.IntroScreen.route){
          IntroScreen(navController = navController)

       }
        composable(route = Screen.ChoseSignupScreen.route){
            ChoseSignupScreen(navController =navController )
        }
        composable(route = Screen.PortfolioScreen.route){
            PortfolioScreen(navController =navController)
        }
        composable(route = Screen.SignUpScreen.route){
            SignUpScreen(navController =navController )
        }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController, userViewModel = userViewModel)
        }
        composable(route = Screen.ExplorerScreen.route) {
            ExplorerScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }

    }
}