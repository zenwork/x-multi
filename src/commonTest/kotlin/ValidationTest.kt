import io.zenwork.x.multi.Validation
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidationTest {

    @Test
    fun validate() {
        val result = Validation().validate("unknown", "something")
        assertEquals("unknown", result.name)
        assertEquals(200, result.status)
        assertEquals("OK", result.message)
        assertEquals("something", result.originalValue)
        assertEquals("nothing to do", result.details)
    }
}
