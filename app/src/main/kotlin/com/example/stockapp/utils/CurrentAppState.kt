package com.example.stockapp.utils

import com.example.stockapp.mvvm.Screen

data class CurrentAppState (
    var showNavigationBar: Boolean = false,
    var currentViewedScreen: Screen = Screen.IntroScreen
)