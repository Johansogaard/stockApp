package com.example.stockapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.stockapp.integration.api.Authentication
import com.example.stockapp.integration.api.AuthenticationState
import com.example.stockapp.integration.database.FirebaseDatabaseConnection
import com.example.stockapp.ui.theme.StockAppTheme
import com.example.stockapp.mvvm.viewModels.BuyViewModel
import com.example.stockapp.mvvm.viewModels.CompetitionViewModel
import com.example.stockapp.mvvm.viewModels.CurrentAppViewModel
import com.example.stockapp.mvvm.viewModels.StocksViewModel
import com.example.stockapp.mvvm.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authentication: Authentication

    @Inject
    lateinit var firebaseDatabaseConnection: FirebaseDatabaseConnection

    val userViewModel: UserViewModel by viewModels()
    val stocksViewModel: StocksViewModel by viewModels()
    val competitionViewModel: CompetitionViewModel by viewModels()
    val currentAppViewModel: CurrentAppViewModel by viewModels()
    val buyViewModel: BuyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                authentication.state.collect { authState: AuthenticationState ->
                    val loggedInUser = authState.currentUser
                    Log.i("FirebaseAuth", "User log in status: $loggedInUser")

                    // Perform additional actions based on user status
                    if (loggedInUser != null) {
                        Log.i("FirebaseAuth", "Preparing repositories...")
                    } else {
                        Log.i("FirebaseAuth", "Unable to prepare repositories")
                    }
                }
            }
        }

        setContent {
            // now we set the fontFamily = robotoFontFamily
            StockAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val authState by authentication.state.collectAsState()

                    if (!authState.ready) {
                        // Show the UI blocker if the app isn't ready
                        LoadingScreen()
                    } else {

                        MainNavHost(
                            authentication = authentication,
                            userViewModel = userViewModel,
                            stocksViewModel = stocksViewModel,
                            competitionViewModel = competitionViewModel,
                            currentAppViewModel = currentAppViewModel,
                            buyViewModel = buyViewModel
                        )
                    }
                }
            }
        }
    }
}