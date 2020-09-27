import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VirtualMemoryTest {

    @Test
    fun countSubstitutionsALLELEMENTSARENULL() {
        val result = listOf<Int?>(null, null, null, null, null, null, null, null, null)
        Assertions.assertEquals(countSubstitutions(result), 0)
    }
    @Test
    fun countSubstitutionsALLELEMENTSARENOTNULL() {
        val result = listOf<Int?>(1, 2, 4, 3, 1, 2, 4)
        Assertions.assertEquals(countSubstitutions(result), 7)
    }
    @Test
    fun countSubstitutionsOnlyFewNulls() {
        val result = listOf<Int?>(null, 2, null, 3, 1, 2, null)
        Assertions.assertEquals(countSubstitutions(result), 4)
    }
}