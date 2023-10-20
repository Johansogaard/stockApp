package com.example.stockapp

sealed class Screen(val route: String) {
    object IntroScreen: Screen("intro_screen")
    object LoginScreen: Screen("chose_signup_screen")
    object PortfolioScreen: Screen("portfolio_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$args")
            }
        }
    }
}
