import сomponents.readInputFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test


@Tag("unitTest")
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
}