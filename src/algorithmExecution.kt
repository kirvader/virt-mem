import сomponents.Act
import java.util.*

// Функция симулирующая работу OPT алгоритма

enum class Algorithms {
    FIFO, LRU, OPT
}

/*
Это функция которая симулирует работу выбранного алгоритма. Сначала она заводит коллекции соответствующие для каждого
алгоритма. Для FIFO - это очередь, для LRU - это массив последнего вхождения страницы в соответствующем кадре в
последовательность страниц и для OPT - это массив следующего вхождения каждой страницы в последовательность обращений.
В ходе алгоритма будет обновляться только соответствующая коллекция.
*/
fun executeAlgorithm(act : Act, algorithm : Algorithms): List<Int> {
    val pageInFrame = MutableList(act.framesNumber + 1) {0}
    val frameForPage = MutableList(act.pageNumber + 1) {0}
    val substitutionsList = mutableListOf<Int>() // массив ответов
    val secondaryQueueForFIFO : Queue<Int> = LinkedList()
    val secondaryListForLRU = MutableList(act.framesNumber + 1) {-1}
    val secondaryListForOPT = MutableList(act.pageNumber + 1) {act.pages.size}
    for (indexInAct in (0..act.pages.lastIndex)) {
        val nextPage = act.pages[indexInAct]
        if (secondaryListForOPT[nextPage] > indexInAct) {
            secondaryListForOPT[nextPage] = indexInAct
        }
    }
    for (indexInAct in (0..act.pages.lastIndex)) {
        val nextPage = act.pages[indexInAct]
        // Если эта страница уже загружена, то ничего делать не нужно
        if (frameForPage[nextPage] != 0) {
            when (algorithm) {
                Algorithms.OPT -> { secondaryListForOPT[nextPage] = findNextAppeal(nextPage, indexInAct, act) }
                Algorithms.LRU -> { secondaryListForLRU[frameForPage[nextPage]] = indexInAct }
            }
            substitutionsList.add(0)
            continue
        }
        // Найдем свободный кадр оперативной памяти если он есть
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != 0) {
            pageInFrame[indexOfEmptyFrame] = nextPage
            frameForPage[nextPage] = indexOfEmptyFrame
            substitutionsList.add(indexOfEmptyFrame)
            when (algorithm) {
                Algorithms.OPT -> { secondaryListForOPT[nextPage] = findNextAppeal(nextPage, indexInAct, act) }
                Algorithms.LRU -> { secondaryListForLRU[indexOfEmptyFrame] = indexInAct }
                Algorithms.FIFO -> { secondaryQueueForFIFO.add(nextPage) }
            }

            secondaryListForOPT[nextPage] = findNextAppeal(nextPage, indexInAct, act)
            continue
        }
        // Если все кадры заняты, но нужно какой-то заменить, тогда найдем оптимальный в соответствии с выбранным алгоритмом
        val pageToChange : Int = when (algorithm) {
            Algorithms.OPT -> { findOPTPage(secondaryListForOPT, frameForPage) }
            Algorithms.LRU -> { pageInFrame[findLRUFrame(secondaryListForLRU)] }
            Algorithms.FIFO -> { secondaryQueueForFIFO.remove() }
        }
        substitutionsList.add(frameForPage[pageToChange])
        pageInFrame[frameForPage[pageToChange]] = nextPage
        frameForPage[nextPage] = frameForPage[pageToChange]
        frameForPage[pageToChange] = 0
        when (algorithm) {
            Algorithms.OPT -> { secondaryListForOPT[pageToChange] = findNextAppeal(pageToChange, secondaryListForOPT[pageToChange], act)
                secondaryListForOPT[nextPage] = findNextAppeal(nextPage, indexInAct, act) }
            Algorithms.LRU -> { secondaryListForLRU[frameForPage[nextPage]] = indexInAct }
            Algorithms.FIFO -> { secondaryQueueForFIFO.add(nextPage) }
        }
    }
    return substitutionsList
}