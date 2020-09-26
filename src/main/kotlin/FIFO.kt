import Components.Act
import java.util.*

// Функция, которая по данным, о том какими страницами заняты кадры возвращает индекс первого свободного кадра
fun findIndexOfEmptyFrame(pageInFrame : List<Int?>): Int? {
    for (index in 1..(pageInFrame.size - 1)) {
        if (pageInFrame[index] == null) return index
    }
    return null
}

fun executeFIFO(act : Act): MutableList<Int?> {
    // Это очередь состоящая из страниц, которые занимают какой-то кадр
    val currentListOfPages : Queue<Int> = LinkedList<Int>()
    val frameForPage = MutableList<Int?>(act.pages.size + 1) {null}
    val pageInFrame = MutableList<Int?>(act.framesNumber + 1) {null}
    var substitutionsList = mutableListOf<Int?>()
    for (nextPage in act.pages) {
        // если страница уже загружена в оперативную память, то ничего не делаем
        if (currentListOfPages.contains(nextPage)) {
            substitutionsList.add(null)
            continue
        }
        // Выясним есть ли пустой кадр; если есть, то положим туда страницу
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != null) {
            currentListOfPages.add(nextPage)
            frameForPage[nextPage] = indexOfEmptyFrame
            pageInFrame[indexOfEmptyFrame] = nextPage
            substitutionsList.add(indexOfEmptyFrame)
            continue
        }
        // Здесь все кадры заняты, поэтому находим первый элемент, который попал в оперативную память, выкидываем его и обновляем все что с ним было связано
        val removedPage = currentListOfPages.remove()
        substitutionsList.add(frameForPage[removedPage])
        pageInFrame[frameForPage[removedPage]!!] = nextPage
        frameForPage[nextPage] = frameForPage[removedPage]
        currentListOfPages.add(nextPage)
    }
    return substitutionsList

}