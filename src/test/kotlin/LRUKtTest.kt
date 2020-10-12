import сomponents.readInputFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test


@Tag("unitTest")
internal class LRUKtTest {

    @Test
    fun findLRUFrameFindFirst() {
        val currentLastAppealInFrameArray = listOf(0, 1, 3, 2, 4)
        Assertions.assertEquals(findLRUFrame(currentLastAppealInFrameArray), 1)
    }

    @Test
    fun findLRUFrameFindLast() {
        val currentLastAppealInFrameArray = listOf(0, 10, 3, 2, 4, 1)
        Assertions.assertEquals(findLRUFrame(currentLastAppealInFrameArray), 5)
    }

    @Test
    fun findLRUFrameFindInMiddle() {
        val currentLastAppealInFrameArray = listOf(0, 10, 3, 2, 4, 21, 6, 8)
        Assertions.assertEquals(findLRUFrame(currentLastAppealInFrameArray), 3)
    }


}