import сomponents.Act
import java.util.*

// Функция, которая по данным, о том какими страницами заняты кадры возвращает индекс первого свободного кадра
fun findIndexOfEmptyFrame(pageInFrame : List<Int>): Int =
        (1..pageInFrame.lastIndex).firstOrNull { pageInFrame[it] == 0 }
                ?: 0

// Функция, которая симулирует работу FIFO алгоритма
fun executeFIFO(act : Act): List<Int> {
    // Это очередь состоящая из страниц, которые занимают какой-то кадр
    val currentListOfPages : Queue<Int> = LinkedList()
    val frameForPage = MutableList(act.pageNumber + 1) {0}
    val pageInFrame = MutableList(act.framesNumber + 1) {0}
    val substitutionsList = mutableListOf<Int>()
    for (nextPage in act.pages) {
        // если страница уже загружена в оперативную память, то ничего не делаем
        if (currentListOfPages.contains(nextPage)) {
            substitutionsList.add(0)
            continue
        }
        // Выясним есть ли пустой кадр; если есть, то положим туда страницу
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != 0) {
            currentListOfPages.add(nextPage)
            frameForPage[nextPage] = indexOfEmptyFrame
            pageInFrame[indexOfEmptyFrame] = nextPage
            substitutionsList.add(indexOfEmptyFrame)
            continue
        }
        // Здесь все кадры заняты, поэтому находим первый элемент, который попал в оперативную память,
        // выкидываем его и обновляем все что с ним было связано
        val removedPage = currentListOfPages.remove()

        substitutionsList.add(frameForPage[removedPage])
        pageInFrame[frameForPage[removedPage]] = nextPage
        frameForPage[nextPage] = frameForPage[removedPage]
        currentListOfPages.add(nextPage)
        frameForPage[removedPage] = 0
    }
    return substitutionsList

}