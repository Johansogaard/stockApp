package com.example.stockapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockapp.screens.IntroScreen
import com.example.stockapp.screens.LoginScreen
import com.example.stockapp.screens.PortfolioScreen

@Composable
fun MainNavHost() {
    val navController = rememberNavController();

    NavHost(navController = navController, startDestination = Screen.IntroScreen.route)
    {
       composable(route = Screen.IntroScreen.route){
          IntroScreen(navController = navController)

       }
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController =navController )
        }
        composable(route = Screen.PortfolioScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name")
                {
                    type = NavType.StringType
                    defaultValue = "null"
                    nullable = true
                }
            )
            ){ entry ->
          PortfolioScreen(name = entry.arguments?.getString("name"))
        }

    }
}