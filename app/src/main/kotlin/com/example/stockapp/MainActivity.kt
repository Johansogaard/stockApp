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
import com.example.stockapp.integration.firebase.authentication.Authentication
import com.example.stockapp.integration.firebase.authentication.AuthenticationState
import com.example.stockapp.integration.firebase.database.FirebaseDatabaseConnection
import com.example.stockapp.integration.stockapi.StockApi
import com.example.stockapp.mvvm.buy.buy.BuyViewModel
import com.example.stockapp.mvvm.competition.CompetitionViewModel
import com.example.stockapp.mvvm.index.IndexViewModel
import com.example.stockapp.mvvm.loading.LoadingScreen
import com.example.stockapp.mvvm.order.OrderViewModel
import com.example.stockapp.mvvm.portfolio.PortfolioViewModel
import com.example.stockapp.mvvm.search.explorer.ExplorerViewModel
import com.example.stockapp.mvvm.search.search.SearchViewModel
import com.example.stockapp.mvvm.start.intro.IntroViewModel
import com.example.stockapp.mvvm.start.login.LoginViewModel
import com.example.stockapp.mvvm.start.signup.SignupViewModel
import com.example.stockapp.mvvm.stock.StockViewModel
import com.example.stockapp.mvvm.watch.WatchViewModel
import com.example.stockapp.ui.theme.StockAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authentication: Authentication

    @Inject
    lateinit var firebaseDatabaseConnection: FirebaseDatabaseConnection

    @Inject
    lateinit var stockApi: StockApi

    @Inject
    lateinit var userRepository: com.example.stockapp.repositories.user.UserRepository

    @Inject
    lateinit var stockRepository: com.example.stockapp.repositories.user.UserRepository

    private val buyViewModel: BuyViewModel by viewModels()
    private val competitionViewModel: CompetitionViewModel by viewModels()
    private val indexViewModel: IndexViewModel by viewModels()
    private val orderViewModel: OrderViewModel by viewModels()
    private val portfolioViewModel: PortfolioViewModel by viewModels()
    private val explorerViewModel: ExplorerViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()
    private val introViewModel: IntroViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val signupViewModel: SignupViewModel by viewModels()
    private val stocksViewModel: StockViewModel by viewModels()
    private val watchViewModel: WatchViewModel by viewModels()

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
                            loginViewModel = loginViewModel,
                            stockViewModel = stocksViewModel,
                            competitionViewModel = competitionViewModel,
                            signupViewModel = signupViewModel,
                            buyViewModel = buyViewModel,
                            portfolioViewModel = portfolioViewModel,
                            explorerViewModel = explorerViewModel,
                            indexViewModel = indexViewModel,
                            orderViewModel = orderViewModel,
                            searchViewModel = searchViewModel,
                            introViewModel = introViewModel,
                            watchViewModel = watchViewModel,
                        )
                    }
                }
            }
        }
    }
}