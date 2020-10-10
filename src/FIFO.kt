import сomponents.Act
import java.util.*
// вспомогательная функция для FIFO
// Функция, которая по данным, о том какими страницами заняты кадры возвращает индекс первого свободного кадра
fun findIndexOfEmptyFrame(pageInFrame : List<Int>): Int =
        (1..pageInFrame.lastIndex).firstOrNull { pageInFrame[it] == 0 }
                ?: 0