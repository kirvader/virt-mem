import сomponents.readInputFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class FIFOKtTest {

    @Test
    fun findIndexOfEmptyFrameBEGINNINGOFALGO() {
        val currentFrameForPageArray = listOf(0, 0, 0, 0)
        Assertions.assertEquals(findIndexOfEmptyFrame(currentFrameForPageArray), 1)
    }
    @Test
    fun findIndexOfEmptyFrameINTHEMIDDLE() {
        val currentFrameForPageArray = listOf(0, 1, 2, 0, 0, 0)
        Assertions.assertEquals(findIndexOfEmptyFrame(currentFrameForPageArray), 3)
    }
    @Test
    fun findIndexOfEmptyFrameALLFRAMESAREWITHPAGES() {
        val currentFrameForPageArray = listOf(0, 1, 2, 4, 3, 6)
        Assertions.assertEquals(findIndexOfEmptyFrame(currentFrameForPageArray), 0)
    }

    @Test
    fun executeFIFOONTEST1Sample1() { // Тест с циклической последовательностью обращений(см. тест 1)
        val acts = readInputFile("data/tests/test1.txt")
        val actual : List<Int> = executeFIFO(acts[0])
        val expected : List<Int> = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeFIFOONTEST2Sample1() { // Тест, где есть места, когда не нужно что-то менять(операция 1 типа)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeFIFO(acts[0])
        val expected : List<Int> = listOf(1, 2, 3, 0, 1, 2, 3, 0)
        Assertions.assertEquals(expected, actual)
    }
    @Test
    fun executeFIFOONTEST2Sample3() { // Тест с перемешанной последовательностью обращений(см. тест 2 пример 3)
        val acts = readInputFile("data/tests/test2.txt")
        val actual : List<Int> = executeFIFO(acts[2])
        val expected : List<Int> = listOf(1, 2, 1, 2, 1, 2, 1, 2)
        Assertions.assertEquals(expected, actual)
    }
}