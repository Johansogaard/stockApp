import com.example.stockapp.stockApi.ApiTest
import com.example.stockapp.stockApi.getcurrentvalue
import org.junit.Assert
import org.junit.Test
import java.util.Arrays

class GetCurrentvalueTest {
    @Test
    fun testGetCurrentvalueWithNonEmptyList() {
        val stockData = Arrays.asList(
            Triple(1.5f, 2.5f, 3.5f),
            Triple(4.5f, 5.5f, 6.5f)
        )
        val currentValue: Float = getcurrentvalue(stockData)
        Assert.assertEquals(4.5f, currentValue, 0.0f)
    }

    @Test
    fun testGetCurrentvalueWithEmptyList() {
        val stockData = emptyList<Triple<Float, Float, Float>>()
        val currentValue: Float = getcurrentvalue(stockData)
        Assert.assertEquals(0f, currentValue, 0.0f)
    }
}