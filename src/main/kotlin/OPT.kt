import Components.Act

fun findOPTPage(nextAppeal : List<Int>, frameForPage : List<Int?>) : Int {
    var optimalPage = 1
    for (pageNumber in 1..(nextAppeal.size - 1)) {
        if (frameForPage[pageNumber] == null) continue
        if (nextAppeal[pageNumber] > nextAppeal[optimalPage]) {
            optimalPage = pageNumber
        }
    }
    return optimalPage
}

fun findNextAppeal(page : Int, currentAppeal : Int, nextAppeal: List<Int>, act: Act): Int {
    var indexInAct = currentAppeal + 1
    while (indexInAct < act.pages.size && act.pages[indexInAct] != page) {
        indexInAct++
    }
    return indexInAct
}

fun executeOPT(act : Act): MutableList<Int?> {
    val pageInFrame = MutableList<Int?>(act.framesNumber + 1) {null}
    val frameForPage = MutableList<Int?>(act.pages.size + 1) {null}
    var substitutionsList = mutableListOf<Int?>()
    val nextAppeal = MutableList<Int>(act.pages.size + 1) {act.pages.size + 1}
    for (indexInAct in (0..act.pages.size - 1)) {
        val nextPage = act.pages[indexInAct]
        if (nextAppeal[nextPage] > indexInAct) {
            nextAppeal[nextPage] = indexInAct
        }
    }
    for (indexInAct in (0..act.pages.size - 1)) {
        val nextPage = act.pages[indexInAct]
        // Если эта страница уже загружена, то ничего делать не нужно
        if (pageInFrame.contains(nextPage)) {
            nextAppeal[nextPage] = findNextAppeal(nextPage, indexInAct, nextAppeal, act)
            substitutionsList.add(null)
            continue
        }
        // Найдем свободный кадр оперативной памяти если он есть
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != null) {
            pageInFrame[indexOfEmptyFrame] = nextPage
            frameForPage[nextPage] = indexOfEmptyFrame
            substitutionsList.add(indexOfEmptyFrame)
            nextAppeal[nextPage] = findNextAppeal(nextPage, indexInAct, nextAppeal, act)
            continue
        }
        // Если все кадры заняты, но нужно какой-то заменить, тогда найдем оптимальный(тот к которому дольше всех не будет обращений)
        val optPage = findOPTPage(nextAppeal, frameForPage)
        substitutionsList.add(frameForPage[optPage])
        pageInFrame[frameForPage[optPage]!!] = nextPage
        frameForPage[nextPage] = frameForPage[optPage]
        frameForPage[optPage] = null
        nextAppeal[optPage] = findNextAppeal(optPage, nextAppeal[optPage], nextAppeal, act)
        nextAppeal[nextPage] = findNextAppeal(nextPage, indexInAct, nextAppeal, act)
    }
    return substitutionsList
}