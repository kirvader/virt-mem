import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

import сomponents.readInputFile


@Tag("integrationTest")
internal class AlgorithmExecutionKtTest {

    @Test
    fun executeFIFOONTEST1Sample1() { // Тест с циклической последовательностью обращений(см. тест 1)
        val acts = readInputFile("data/tests/test1.txt")
        val actual : List<Int> = executeAlgorithm(acts[0], Algorithms.FIFO)
        val expected : List<Int> = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeFIFOONTEST2Sample1() { // Тест, где есть места, когда не нужно что-то менять(операция 1 типа)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeAlgorithm(acts[0], Algorithms.FIFO)
        val expected : List<Int> = listOf(1, 2, 3, 0, 1, 2, 3, 0)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeFIFOONTEST2Sample3() { // Тест с перемешанной последовательностью обращений(см. тест 2 пример 3)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeAlgorithm(acts[2], Algorithms.FIFO)
        val expected : List<Int> = listOf(1, 2, 1, 2, 1, 2, 1, 2)
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun executeLRUONTEST1Sample1() { // Тест с циклической последовательностью обращений(см. тест 1)
        val acts = readInputFile("data/tests/test1.txt")
        val actual : List<Int> = executeAlgorithm(acts[0], Algorithms.LRU)
        val expected : List<Int> = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeLRUONTEST2Sample1() { // Тест, где есть места, когда не нужно что-то менять(операция 1 типа)(см. тест 2 пример 1)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeAlgorithm(acts[0], Algorithms.LRU)
        val expected : List<Int> = listOf(1, 2, 3, 0, 1, 3, 0, 0)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeLRUONTEST2Sample3() { // Тест с миксованной последовательностью обращений(см. тест 2 пример 3)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeAlgorithm(acts[2], Algorithms.LRU)
        val expected : List<Int> = listOf(1, 2, 1, 2, 1, 2, 1, 2)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeOPTONTEST1Sample1() { // Тест с циклической последовательностью обращений(см. тест 1)
        val acts = readInputFile("data/tests/test1.txt")
        val actual : List<Int> = executeAlgorithm(acts[0], Algorithms.OPT)
        val expected : List<Int> = listOf(1, 2, 3, 4, 4, 0, 0, 0, 3, 0, 0, 0, 1, 0, 0)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeOPTONTEST2Sample1() { // Тест, где есть места, когда не нужно что-то менять в FIFO(операция 1 типа)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeAlgorithm(acts[0], Algorithms.OPT)
        val expected : List<Int> = listOf(1, 2, 3, 0, 3, 0, 0, 0)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeOPTONTEST2Sample3() { // Тест с миксованной последовательностью обращений(см. тест 2 пример 3)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeAlgorithm(acts[2], Algorithms.OPT)
        val expected : List<Int> = listOf(1, 2, 1, 1, 0, 2, 2, 2)
        Assertions.assertEquals(expected, actual)
    }
}