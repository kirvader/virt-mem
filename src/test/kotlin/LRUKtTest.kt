import Components.readInputFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LRUKtTest {

    @Test
    fun findLRUFrameFindFirst() {
        val currentLastAppealInFrameArray = listOf(null, 1, 3, 2, 4)
        assertEquals(findLRUFrame(currentLastAppealInFrameArray), 1)
    }

    @Test
    fun findLRUFrameFindLast() {
        val currentLastAppealInFrameArray = listOf(null, 10, 3, 2, 4, 1)
        assertEquals(findLRUFrame(currentLastAppealInFrameArray), 5)
    }

    @Test
    fun findLRUFrameFindInMiddle() {
        val currentLastAppealInFrameArray = listOf(null, 10, 3, 2, 4, 21, 6, 8)
        assertEquals(findLRUFrame(currentLastAppealInFrameArray), 3)
    }

    @Test
    fun executeLRUONTEST1Sample1() { // Тест с циклической последовательностью обращений(см. тест 1)
        val acts = readInputFile("data/tests/test1.txt")
        val actual : List<Int?> = executeLRU(acts[0])
        val expected : List<Int?> = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeLRUONTEST2Sample1() { // Тест, где есть места, когда не нужно что-то менять(операция 1 типа)(см. тест 2 пример 1)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int?> = executeLRU(acts[0])
        val expected : List<Int?> = listOf(1, 2, 3, null, 1, 3, null, null)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeLRUONTEST2Sample3() { // Тест с миксованной последовательностью обращений(см. тест 2 пример 3)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int?> = executeLRU(acts[2])
        val expected : List<Int?> = listOf(1, 2, 1, 2, 1, 2, 1, 2)
        Assertions.assertEquals(expected, actual)
    }
}