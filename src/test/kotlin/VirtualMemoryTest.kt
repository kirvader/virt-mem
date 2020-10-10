import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VirtualMemoryTest {

    @Test
    fun countSubstitutionsALLELEMENTSARENULL() {
        val result = listOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0)
        Assertions.assertEquals(countSubstitutions(result), 0)
    }
    @Test
    fun countSubstitutionsALLELEMENTSARENOTNULL() {
        val result = listOf<Int>(1, 2, 4, 3, 1, 2, 4)
        Assertions.assertEquals(countSubstitutions(result), 7)
    }
    @Test
    fun countSubstitutionsOnlyFewNulls() {
        val result = listOf<Int>(0, 2, 0, 3, 1, 2, 0)
        Assertions.assertEquals(countSubstitutions(result), 4)
    }
}