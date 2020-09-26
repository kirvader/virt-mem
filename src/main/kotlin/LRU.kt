import Components.Act



// Функция которая находит кадр, в котором находится страница к которой дольше всего не было обращений
// Внимание! Работает только на проверенном массиве, то есть когда все кадры забиты какими-то страницами
fun findLRUFrame(lastAppealInFrame: List<Int?>) : Int {
    var lru = 1
    for (frame in 1..(lastAppealInFrame.size - 1)) {
        if (lastAppealInFrame[frame]!! < lastAppealInFrame[lru]!!) {
            lru = frame
        }
    }
    return lru
}

fun executeLRU(act : Act): MutableList<Int?> {
    val pageInFrame = MutableList<Int?>(act.framesNumber + 1) {null}
    val lastAppealInFrame = MutableList<Int?>(act.framesNumber + 1) {null}
    var substitutionsList = mutableListOf<Int?>()

    for (indexInAct in (0..act.pages.size - 1)) {
        val nextPage = act.pages[indexInAct]
        // Если эта страница уже загружена, то ничего делать не нужно
        if (pageInFrame.contains(nextPage)) {
            substitutionsList.add(null)
            continue
        }
        // Найдем свободный кадр оперативной памяти если он есть
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != null) {
            pageInFrame[indexOfEmptyFrame] = nextPage
            substitutionsList.add(indexOfEmptyFrame)
            lastAppealInFrame[indexOfEmptyFrame] = indexInAct // indexInAct - это своеобразный секундомер
            continue
        }
        // Найдем LRU кадр и заменим страницу лежащую в нем на текущую
        val lruFrame = findLRUFrame(lastAppealInFrame)
        substitutionsList.add(lruFrame)
        pageInFrame[lruFrame] = nextPage
        lastAppealInFrame[lruFrame] = indexInAct
    }
    return substitutionsList
}