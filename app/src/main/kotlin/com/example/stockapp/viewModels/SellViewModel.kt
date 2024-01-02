import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SellViewModel(private val initialUiState: SellUiState = SellUiState()) : ViewModel() {
    private val _uiState = MutableStateFlow(initialUiState)
    val uiState: StateFlow<SellUiState> = _uiState.asStateFlow()

    var currentAmount by mutableStateOf("")

    val ownedShares = MutableStateFlow(0) // Replace with actual logic to get owned shares
    val marketPrice = MutableStateFlow(0.0) // Placeholder value, replace with actual market price fetching logic

    init {
        // You can initialize marketPrice with a real value fetched from somewhere
        marketPrice.value = 1726.88 // Example value
    }

    fun calculatePotentialRevenue(numberOfShares: Int, marketPrice: Double): Double {
        return numberOfShares * marketPrice
    }

    fun updateAmount(amount: Int) {
        val newAmount = currentAmount + amount.toString()

        if (newAmount.length <= 7) {
            currentAmount = newAmount
            updateUiState()
        }
    }

    private fun updateUiState() {
        val numberOfShares = currentAmount.toIntOrNull() ?: 0
        val potentialRevenue = calculatePotentialRevenue(numberOfShares, marketPrice.value)
        val isOverMax = numberOfShares > ownedShares.value
        _uiState.update { it.copy(isMaxAmount = isOverMax, potentialRevenue = potentialRevenue) }
    }

    fun removeLastDigit() {
        if (currentAmount.isNotEmpty()) {
            currentAmount = currentAmount.dropLast(1)
            updateUiState()
        }
    }

    fun executeSale() {
        val numberOfShares = currentAmount.toIntOrNull() ?: 0
        if (numberOfShares <= ownedShares.value) {
            // Proceed with sale logic
            // This will likely involve updating a repository or making a network call
        } else {
            // Handle error: trying to sell more shares than owned
        }
    }
}

// The UI state for the sell screen
data class SellUiState(
    val isMaxAmount: Boolean = false,
    val potentialRevenue: Double = 0.0,
    val ownedShares: Int = 0
    // Include other state properties as needed
)
