package com.example.stockapp.mvvm.uiModels

import com.example.stockapp.mvvm.screens.Screen

data class CurrentAppState (
    var showNavigationBar: Boolean = false,
    var currentViewedScreen: Screen = Screen.IntroScreen
)