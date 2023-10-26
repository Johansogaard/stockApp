package com.example.stockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.stockapp.ui.theme.StockAppTheme
import com.example.stockapp.viewModels.CompetitionViewModel
import com.example.stockapp.viewModels.CurrentAppViewModel
import com.example.stockapp.viewModels.StockViewModel
import com.example.stockapp.viewModels.UserViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {

                val userViewModel: UserViewModel by viewModels()
                val stockViewModel: StockViewModel by viewModels()
                val competitionViewModel: CompetitionViewModel by viewModels()
                val currentAppViewModel: CurrentAppViewModel by viewModels()

                MainNavHost(
                    userViewModel = userViewModel,
                    stockViewModel = stockViewModel,
                    competitionViewModel = competitionViewModel,
                    currentAppViewModel = currentAppViewModel
                )
            }
        }

    }

}