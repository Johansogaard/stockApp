package com.example.stockapp

sealed class Screen(val route: String) {
    object IntroScreen: Screen("intro_screen")
    object ChoseSignupScreen: Screen("chose_signup_screen")
    object PortfolioScreen: Screen("portfolio_screen")
    object SignUpScreen: Screen("signup_screen")
    object LoginScreen: Screen("login_screen")

    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$args")
            }
        }
    }
}