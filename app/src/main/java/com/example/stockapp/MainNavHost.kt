package com.example.stockapp

import AuthenticationManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockapp.screens.IntroScreen
import com.example.stockapp.screens.LoginScreen
import com.example.stockapp.screens.PortfolioScreen
import com.example.stockapp.screens.SignUpScreen

@Composable
fun MainNavHost() {
    val navController = rememberNavController();
    val authManager = remember { AuthenticationManager() }
    val currentUser = authManager.getCurrentUser()

    NavHost(navController = navController, startDestination = Screen.IntroScreen.route)
    {
       composable(route = Screen.IntroScreen.route){
          IntroScreen(navController = navController)

       }
        composable(route = Screen.ChoseSignupScreen.route){
            LoginScreen(navController =navController )
        }
        composable(route = Screen.PortfolioScreen.route){
            PortfolioScreen(navController =navController,authManager = authManager)
        }
        composable(route = Screen.SignUpScreen.route){
            SignUpScreen(navController =navController,authManager =authManager )
        }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController =navController,authManager =authManager )
        }

    }
}