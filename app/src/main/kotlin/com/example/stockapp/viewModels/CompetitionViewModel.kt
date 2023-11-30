package com.example.stockapp.viewModels

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stockapp.models.CompetitionState

class CompetitionViewModel : ViewModel() {

    /* State held by this ViewModel
     Note:  The View-Model holds its OWN state.
            This ensures encapsulation, and allows us to
            expose only the state we want to expose to the view.
     */
    private val _state = mutableStateOf(CompetitionState())
    val state: State<CompetitionState> get() = _state

    // A simple methods - This is how (and where) we would change the state
    fun updateScore(newScore: Int) {
        _state.value = _state.value.copy(score = newScore)
    }
}
