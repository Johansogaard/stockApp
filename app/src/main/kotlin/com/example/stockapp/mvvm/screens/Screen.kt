package com.example.stockapp.mvvm.screens

sealed class Screen(val route: String) {
    object IntroScreen: Screen("intro_screen")
    object ChoseSignupScreen: Screen("chose_signup_screen")
    object PortfolioScreen: Screen("portfolio_screen")
    object SignUpScreen: Screen("signup_screen")
    object LoginScreen: Screen("login_screen")
    object ExplorerScreen: Screen("explorer_screen")
    object SearchScreen: Screen("search_screen")
    object BuyScreen1: Screen("buy_screen1")
    object BuyScreen2: Screen("buy_screen2")
    object BuyScreen3: Screen("buy_screen3")

    object WatchScreen: Screen("watch_screen")
    object OrderScreen: Screen("order_screen")
    object TransactionScreen: Screen("transaction_screen")
    object StockViewScreen: Screen("stockView_screen")

    object IndexScreen: Screen("index_screen")
    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$args")
            }
        }
    }
}
