package сomponents

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

@Tag("unitTest")
internal class OutputComponentKtTest {
    @Test
    fun createPathToOutputFileRelative() {
        Assertions.assertEquals(createPathToOutputFile("data/input.txt", "Relative"), File("data/outputRelative.txt"))
    }
    @Test
    fun createPathToOutputFileRelativeGenerated() {
        Assertions.assertEquals(createPathToOutputFile("data/inputGenerated.txt", "Relative"), File("data/outputRelative.txt"))
    }
    @Test
    fun createPathToOutputFileAbsolute() {
        Assertions.assertEquals(createPathToOutputFile("/home/kira/prog-2020-virt-mem-kirvader/data/input.txt", "Relative"), File("/home/kira/prog-2020-virt-mem-kirvader/data/outputRelative.txt"))
    }
}