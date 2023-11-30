import com.example.stockapp.stockApi.findMinValue
import org.junit.Assert
import org.junit.Test
import java.util.Arrays

class FindMinValueTest {
    @Test
    fun testFindMinValue() {
        val data = Arrays.asList(
            Triple(1.5f, 2.5f, 3.5f),
            Triple(4.5f, 5.5f, 0.5f)
        )
        val minValue: Float = findMinValue(data)
        Assert.assertEquals(0.5f, minValue, 0.0f)
    }

    @Test
    fun testFindMinValueEmptyList() {
        val data: List<Triple<Float, Float, Float>> = mutableListOf()
        val minValue: Float = findMinValue(data)
        Assert.assertEquals(0f, minValue, 0.0f)
    }
}