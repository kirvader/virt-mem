import сomponents.Act

// Функция которая находит кадр, в котором находится страница к которой дольше всего не было обращений
// Внимание! Работает только на проверенном массиве, то есть когда все кадры забиты какими-то страницами
fun findLRUFrame(lastAppealInFrame: List<Int>) : Int {
    var lru = 1
    for (frame in 1..lastAppealInFrame.lastIndex) {
        if (lastAppealInFrame[frame] < lastAppealInFrame[lru]) {
            lru = frame
        }
    }
    return lru
}

// Функция, которая симулирует работу LRU алгоритма
fun executeLRU(act : Act): List<Int> {
    // страницы и кадры нумеруются с единицы
    val pageInFrame = MutableList<Int>(act.framesNumber + 1) {0} // текущая страница в соответствующем кадре
    val frameForPage = MutableList<Int>(act.pageNumber + 1) {0} // кадр в котором содержится данная страница
    // последнее вхождение страницы в соответствующем кадре в последовательность страниц
    val lastAppealInFrames = MutableList(act.framesNumber + 1) {-1}
    val substitutionsList = mutableListOf<Int>() // Запись симуляции

    for (indexInAct in 0..act.pages.lastIndex) {
        val nextPage = act.pages[indexInAct]
        // Если эта страница уже загружена, то ничего делать не нужно
        if (frameForPage[nextPage] != 0) {
            substitutionsList.add(0)
            lastAppealInFrames[frameForPage[nextPage]] = indexInAct
            continue
        }
        // Найдем свободный кадр оперативной памяти если он есть
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != 0) {
            pageInFrame[indexOfEmptyFrame] = nextPage
            frameForPage[nextPage] = indexOfEmptyFrame
            substitutionsList.add(indexOfEmptyFrame)
            lastAppealInFrames[indexOfEmptyFrame] = indexInAct // indexInAct - это своеобразный секундомер
            continue
        }
        // Найдем LRU кадр и заменим страницу лежащую в нем на текущую
        val lruFrame = findLRUFrame(lastAppealInFrames)
        substitutionsList.add(lruFrame)
        frameForPage[pageInFrame[lruFrame]] = 0 // т.к. мы ее заменяем, значит там кто-то находился
        pageInFrame[lruFrame] = nextPage
        frameForPage[nextPage] = lruFrame
        lastAppealInFrames[lruFrame] = indexInAct
    }
    return substitutionsList
}