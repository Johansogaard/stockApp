package com.example.stockapp.data

sealed class Screen(val route: String) {
    object IntroScreen: Screen("intro_screen")
    object ChoseSignupScreen: Screen("chose_signup_screen")
    object PortfolioScreen: Screen("portfolio_screen")
    object SignUpScreen: Screen("signup_screen")
    object LoginScreen: Screen("login_screen")
    object ExplorerScreen: Screen("explorer_screen")
    object SearchScreen: Screen("search_screen")
    object BuyScreen: Screen("buy_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$args")
            }
        }
    }
}
