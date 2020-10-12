import сomponents.Act
import сomponents.readInputFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

@Tag("unitTest")
internal class OPTKtTest {

    @Test
    fun findOPTPageNoMoreAppeal() {
        val nextAppealArray = listOf(5, 1, 2, 5, 3)
        val frameForPageArray = listOf(0, 1, 2, 3, 0)
        Assertions.assertEquals(findOPTPage(nextAppealArray, frameForPageArray), 3)
    }

    @Test
    fun findOPTPageElementWillAppearButNotNow() {
        val nextAppealArray = listOf(5, 1, 3, 2, 4)
        val frameForPageArray = listOf(0, 1, 2, 3, 0)
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


}