import Components.Act



// Функция которая находит кадр, в котором находится страница к которой дольше всего не было обращений
// Внимание! Работает только на проверенном массиве, то есть когда все кадры забиты какими-то страницами
fun findLRUFrame(lastAppealInFrame: List<Int?>) : Int {
    var lru = 0
    for (frame in 0..(lastAppealInFrame.size - 1)) {
        if (lastAppealInFrame[frame]!! < lastAppealInFrame[lru]!!) {
            lru = frame
        }
    }
    return lru
}

fun executeLRU(act : Act): MutableList<Int?> {
   // val frameForPage = MutableList<Int?>(act.pages.size) {null}
    val pageInFrame = MutableList<Int?>(act.framesNumber) {null}
    val lastAppealInFrame = MutableList<Int?>(act.framesNumber) {null}
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
            //frameForPage[nextPage] = indexOfEmptyFrame
            substitutionsList.add(indexOfEmptyFrame + 1)
            lastAppealInFrame[indexOfEmptyFrame] = indexInAct // indexInAct - это своеобразный секундомер
            continue
        }
        // Найдем LRU
        val lruFrame = findLRUFrame(lastAppealInFrame)
        substitutionsList.add(lruFrame)
        pageInFrame[lruFrame] = nextPage
        //frameForPage[nextPage] = lruFrame
        lastAppealInFrame[lruFrame] = indexInAct
    }
    return substitutionsList
}