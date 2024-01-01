import com.example.stockapp.stockApi.getytd
import org.junit.Assert
import org.junit.Test
import java.util.Arrays

class GetytdTest {
    @Test
    fun testGetytd() {
        val currentData = Arrays.asList(
            Triple(5.0f, 6.0f, 7.0f)
        )
        val yearData = Arrays.asList(
            Triple(2.0f, 3.0f, 4.0f)
        )
        val ytd: Float = getytd(currentData, yearData)
        Assert.assertEquals(150f, ytd, 0.0f)
    }

    @Test
    fun testGetytdEmptyList() {
        val currentData: List<Triple<Float, Float, Float>> = mutableListOf()
        val yearData: List<Triple<Float, Float, Float>> = mutableListOf()
        val ytd: Float = getytd(currentData, yearData)
        Assert.assertEquals(0f, ytd, 0.0f)
    }
}