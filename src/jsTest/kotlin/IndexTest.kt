import io.zenwork.x.multi.Calculator
import kotlin.test.Test
import kotlin.test.assertEquals

class TestCalculator {
    @Test
    fun add() {
        assertEquals(10, Calculator().add(5,5).equalz())
    }
}
