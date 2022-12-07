import io.zenwork.x.multi.Result
import io.zenwork.x.multi.Rule
import io.zenwork.x.multi.Validation
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidationTest {

    @Test
    fun validateString() {
        val result = Validation().validate("is/string", "something")
        assertEquals("is/string", result.rule)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("something", result.originalValue)
        assertEquals("nothing to do", result.details)
    }

    @Test
    fun validateNull() {
        val result = Validation().validate("is/null", null)
        assertEquals("is/null", result.rule)
        assertEquals(null, result.originalValue)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("nothing to do", result.details)
    }

    @Test
    fun validateNumber() {
        val result = Validation().validate("is/number", -100)
        assertEquals("is/number", result.rule)
        assertEquals(-100, result.originalValue)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("nothing to do", result.details)
    }

    @Test
    fun validateNotNumber() {
        val result = Validation().validate("is/number", "abcd")
        assertEquals("is/number", result.rule)
        assertEquals("abcd", result.originalValue)
        assertEquals(500, result.status)
        assertEquals("NOK", result.message)
        assertEquals("[abcd] failed [is number] assertion", result.details)
    }

    @Test
    fun validatePositiveNumber() {
        val result = Validation().validate("is/number/positive", 100.5)
        assertEquals("is/number/positive", result.rule)
        assertEquals(100.5, result.originalValue)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("nothing to do", result.details)
    }

    @Test
    fun validateNegativeNumber() {
        val result = Validation().validate("is/number/negative", -100.5)
        assertEquals("is/number/negative", result.rule)
        assertEquals(-100.5, result.originalValue)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("nothing to do", result.details)
    }

    class EstimationPokerRule : Rule("is/x/estimation-poker") {
        override fun <Number> rule(value: Number, validator: Validation): Result {
            return when (value) {
                0 -> validator.OK(name, value)
                1 -> validator.OK(name, value)
                2 -> validator.OK(name, value)
                3 -> validator.OK(name, value)
                5 -> validator.OK(name, value)
                8 -> validator.OK(name, value)
                13 -> validator.OK(name, value)
                else -> validator.NOK(name, value)
            }
        }
    }

    @Test
    fun validatePokerEstimationNumber() {
        val validation = Validation(arrayOf(EstimationPokerRule()))
        var result = validation.validate("is/x/estimation-poker", 0)
        assertEquals("is/x/estimation-poker", result.rule)
        assertEquals(0, result.originalValue)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("nothing to do", result.details)

        result = validation.validate("is/x/estimation-poker", 8)
        assertEquals("is/x/estimation-poker", result.rule)
        assertEquals(8, result.originalValue)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("nothing to do", result.details)

        result = validation.validate("is/x/estimation-poker", 13)
        assertEquals("is/x/estimation-poker", result.rule)
        assertEquals(13, result.originalValue)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("nothing to do", result.details)

        result = validation.validate("is/x/estimation-poker", 11)
        assertEquals("is/x/estimation-poker", result.rule)
        assertEquals(11, result.originalValue)
        assertEquals(500, result.status)
        assertEquals("NOK", result.message)
        assertEquals("[11] failed [is estimation poker] assertion", result.details)

    }
}
