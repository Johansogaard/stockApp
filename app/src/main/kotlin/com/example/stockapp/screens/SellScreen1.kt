package com.example.stockapp.screens

import SellViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.stockapp.R

@Composable
fun SellScreen(navController: NavController, sellViewModel: SellViewModel = viewModel()) {
    val sellUiState by sellViewModel.uiState.collectAsState()
    val currentAmount = sellViewModel.currentAmount
    var dialogState by remember { mutableStateOf(Triple(false, "", "")) } // (showDialog, title, message)

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ... Keep the UI elements the same as the buy screen above the first Divider ...

            // Modify below for Sell Context
            CustomTextField(isValueOverMax = sellUiState.isMaxAmount,
                currentAmount = currentAmount,
                onBackspaceClick = { sellViewModel.removeLastDigit() }
            )
            // Selling Amount TextField

            // ... Modify logic for selling, error messages and navigation ...

            // Sell Button
            Button(
                onClick = {
                    // Modify this logic for selling
                },
                // ... keep the button styling the same ...
            ) {
                Text(text = stringResource(R.string.common_continue), modifier = Modifier.padding(7.dp))
            }

            // ... keep the AlertDialog the same ...

            Spacer(modifier = Modifier.height(20.dp))

            // Reuse the Numpad Composable as it is
            Numpad(onDigitClick = {
                sellViewModel.updateAmount(it)
            })
        }
    }
}
