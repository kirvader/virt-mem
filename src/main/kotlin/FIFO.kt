import Components.Act
import java.util.*

fun findIndexOfEmptyFrame(pageInFrame : List<Int?>): Int? {
    for (index in 0..(pageInFrame.size - 1)) {
        if (pageInFrame[index] == null) return index
    }
    return null
}

fun executeFIFO(act : Act): Int {
    // Это очередь состоящая из страниц, которые занимают какой-то кадр
    var currentListOfPages : Queue<Int> = LinkedList<Int>()
    var frameForPage = MutableList<Int?>(act.pages.size) {null}
    var pageInFrame = MutableList<Int?>(act.framesNumber) {null}
    var substitutionsList = mutableListOf<Int?>()
    for (nextPage in act.pages) {
        if (currentListOfPages.contains(nextPage)) {
            substitutionsList.add(null)
            continue
        }
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != null) {
            currentListOfPages.add(nextPage)
            frameForPage[nextPage] = indexOfEmptyFrame
            pageInFrame[indexOfEmptyFrame] = nextPage
            substitutionsList.add(indexOfEmptyFrame)
            continue
        }
        substitutionsList.add(frameForPage[currentListOfPages.remove()])
        pageInFrame[substitutionsList.last()!!]
        currentListOfPages.add(nextPage)
    }
    return substitutionsQuantity
}