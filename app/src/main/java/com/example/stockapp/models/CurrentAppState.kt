package com.example.stockapp.models

import com.example.stockapp.data.Screen

data class CurrentAppState (
    var showNavigationBar: Boolean = false,
    var currentViewedScreen: Screen = Screen.IntroScreen
)