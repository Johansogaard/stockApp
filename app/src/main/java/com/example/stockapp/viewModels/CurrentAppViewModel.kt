package com.example.stockapp.viewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.stockapp.models.CurrentAppState

class CurrentAppViewModel : ViewModel() {

    /* State held by this ViewModel
     Note:  The View-Model holds its OWN state.
            This ensures encapsulation, and allows us to
            expose only the state we want to expose to the view.
     */
    private val _state = mutableStateOf(CurrentAppState())
    val state: State<CurrentAppState> get() = _state

    // A simple method - This is how (and where) we would change the state
    fun showNavigationBar(state: Boolean) {
        _state.value.showNavigationBar = state
        Log.d("nav_bar", "showNavigationBar: $state")
        Log.d("nav_bar",  _state.value.showNavigationBar .toString())
    }
}
