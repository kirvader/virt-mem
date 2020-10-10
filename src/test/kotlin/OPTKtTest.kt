import сomponents.Act
import сomponents.readInputFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class OPTKtTest {

    @Test
    fun findOPTPageNoMoreAppeal() {
        val nextAppealArray = listOf(5, 1, 2, 5, 3)
        val frameForPageArray = listOf(null, 1, 2, 3, null)
        Assertions.assertEquals(findOPTPage(nextAppealArray, frameForPageArray), 3)
    }

    @Test
    fun findOPTPageElementWillAppearButNotNow() {
        val nextAppealArray = listOf(5, 1, 3, 2, 4)
        val frameForPageArray = listOf(null, 1, 2, 3, null)
        Assertions.assertEquals(findOPTPage(nextAppealArray, frameForPageArray), 2)
    }

    @Test
    fun findNextAppealAppealInEnd() {
        val page = 1
        val currentAppeal = 3
        val act = Act(listOf(1, 2, 3, 1, 3, 4, 1), 3, 5)
        Assertions.assertEquals(findNextAppeal(page, currentAppeal, act), 6)
    }

    @Test
    fun findNextAppealAppealNext() {
        val page = 1
        val currentAppeal = 3
        val act = Act(listOf(1, 2, 3, 1, 1, 4, 2), 3, 5)
        Assertions.assertEquals(findNextAppeal(page, currentAppeal, act), 4)
    }

    @Test
    fun findNextAppealWillNotAppeal() {
        val page = 1
        val currentAppeal = 3
        val act = Act(listOf(1, 2, 3, 1, 3, 4, 2), 3, 5)
        Assertions.assertEquals(findNextAppeal(page, currentAppeal, act), 7)
    }

    @Test
    fun executeOPTONTEST1Sample1() { // Тест с циклической последовательностью обращений(см. тест 1)
        val acts = readInputFile("data/tests/test1.txt")
        val actual : List<Int?> = executeOPT(acts[0])
        val expected : List<Int?> = listOf(1, 2, 3, 4, 4, null, null, null, 3, null, null, null, 1, null, null)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeOPTONTEST2Sample1() { // Тест, где есть места, когда не нужно что-то менять в FIFO(операция 1 типа)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int?> = executeOPT(acts[0])
        val expected : List<Int?> = listOf(1, 2, 3, null, 3, null, null, null)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeOPTONTEST2Sample3() { // Тест с миксованной последовательностью обращений(см. тест 2 пример 3)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int?> = executeOPT(acts[2])
        val expected : List<Int?> = listOf(1, 2, 1, 1, null, 1, 1, 1)
        Assertions.assertEquals(expected, actual)
    }
}