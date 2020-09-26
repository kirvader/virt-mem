import Components.Act
import java.util.*

fun executeFIFO(act : Act): Int {
    // Это очередб состоящая из страниц, которые занимают какой-то кадр
    var currentListOfPages : Queue<Int> = LinkedList<Int>()
    var substitutionsQuantity : Int = 0
    for (nextPage in act.pages) {
        if (currentListOfPages.contains(nextPage)) {
            continue
        }
        substitutionsQuantity++
        currentListOfPages.remove()
        currentListOfPages.add(nextPage)
    }
    return substitutionsQuantity
}