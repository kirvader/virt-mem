package Components

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File


internal class OutputComponentKtTest {
    @Test
    fun createPathToOutputFileRelative() {
        Assertions.assertEquals(createPathToOutputFile("data/input.txt", "Relative"), File("data/input.txt/../outputRelative.txt"))
    }
    @Test
    fun createPathToOutputFileAbsolute() {
        Assertions.assertEquals(createPathToOutputFile("C:\\Users\\kirko\\kotlinprojs\\prog-2020-virt-mem-kirvader\\data\\input.txt", "Relative"), File("C:\\Users\\kirko\\kotlinprojs\\prog-2020-virt-mem-kirvader\\data\\input.txt/../outputRelative.txt"))
    }
}