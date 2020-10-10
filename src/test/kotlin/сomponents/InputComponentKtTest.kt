package сomponents

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class InputComponentKtTest {

    @Test
    fun checkIfStringIsNumber12123() {
        Assertions.assertEquals(checkIfStringNumber("12123"), true)
    }
    @Test
    fun checkIfStringIsNumber0() {
        Assertions.assertEquals(checkIfStringNumber("0"), true)
    }
    @Test
    fun checkIfStringIsNumberWrong() {
        Assertions.assertEquals(checkIfStringNumber("231T2"), false)
    }

    @Test
    fun readInputFileTestOneSample() {
        val pages = listOf(1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
        val frames = 4
        val pageNumber = 5
        val acts : List<Act> = listOf(Act(pages, frames, pageNumber))
        Assertions.assertEquals(acts, readInputFile("data/tests/test1.txt"))
    }
    @Test
    fun readInputFileTestManySamples() {
        val pages1 = listOf(1, 2, 4, 2, 3, 1, 2, 3)
        val frames1 = 3
        val pageNumber1 = 4
        val pages2 = listOf(1, 2, 3, 5, 2, 4, 4, 1, 4, 2)
        val frames2 = 4
        val pageNumber2 = 7
        val pages3 = listOf(1, 2, 4, 5, 2, 1, 4, 3)
        val frames3 = 2
        val pageNumber3 = 5
        val acts : List<Act> = listOf(Act(pages1, frames1, pageNumber1), Act(pages2, frames2, pageNumber2), Act(pages3, frames3, pageNumber3))
        Assertions.assertEquals(acts, readInputFile("data/tests/test2.txt"))
    }
}