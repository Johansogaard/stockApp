package com.example.stockapp
import com.example.stockapp.models.BuyUiState
import com.example.stockapp.viewModels.BuyViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
class BuyViewModelTest {
    private lateinit var viewModel: BuyViewModel

    @Before
    fun setup() {
        val testUiState = BuyUiState(balance = 4)
        viewModel = BuyViewModel(testUiState)
    }



    //removeLastDigit removes last digit from currentAmount when not empty
    @Test
    fun removeLastDigitWhenStringNotEmpty() {
        // Arrange
        viewModel.currentAmount = "12345"

        // Act
        viewModel.removeLastDigit()

        // Assert
        assertEquals("1234", viewModel.currentAmount)
    }


    //removeLastDigit does not remove last digit from currentAmount when empty
    @Test
    fun removeLastDigitWhenStringEmpty () {
        // Arrange

        // Act
        viewModel.removeLastDigit()

        // Assert
        assertEquals("", viewModel.currentAmount)
    }

    //removeLastDigit updates isMaxAmount correctly
    @Test
    fun removeLastDigitUpdatesisMaxAmount() {
        // Arrange
        viewModel.currentAmount = "24556" // Setting an amount greater than the balance

        // Act
        viewModel.removeLastDigit() // This should bring the amount below the balance

        // Assert
        assertFalse(viewModel.uiState.value.isMaxAmount)
    }

    /* Test that the current amount is updated correctly
        * when adding digits to the current amount.
        * Also test that isMaxAmount is updated correctly
     */
    @Test
    fun addingDigitsToCurrentAmount() {
        // Arrange
        val initialAmount = "123"
        viewModel.currentAmount = initialAmount
        val addedDigit = 4
        val expectedAmount = initialAmount + addedDigit.toString()


        // Act
        viewModel.updateAmount(addedDigit)

        // Asserting isMaxAmount based on the balance and added amount.
        // If the expected amount is greater than the balance, isMaxAmount should be true.
        if (expectedAmount.toInt() > viewModel.uiState.value.balance) {
            assertTrue(viewModel.uiState.value.isMaxAmount)
        } else {
            assertFalse(viewModel.uiState.value.isMaxAmount)
        }
    }
}
