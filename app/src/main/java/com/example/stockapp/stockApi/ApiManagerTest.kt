import com.example.stockapp.stockApi.ApiManager
import org.junit.Test
import org.junit.Assert.*

class ApiManagerTest {



    @Test
    fun getPopularStocksTest() {
        val stocks = ApiManager.getPopularStocks()
        for(i in 1..stocks.size)
        {
            val stock = stocks[i-1]
            println(stock.name)
            println(stock.c)
        }
        assertNotNull(stocks)
        assertTrue(stocks.isNotEmpty())
        // Add more specific assertions based on your use case
    }

    @Test
    fun technicalIndicatorTest() {
        ApiManager.technicalIndicator()
        // Add assertions for the technicalIndicator result
    }

    @Test
    fun stockCandlesTest() {
        ApiManager.stockCandles()
        // Add assertions for the stockCandles result
    }


}