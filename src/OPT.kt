import сomponents.Act

// Функция которая ищет страницу, в оперативной памяти, которую нужно заменить в соответствии с алгоритмом
fun findOPTPage(nextAppeal : List<Int>, frameForPage : List<Int>) : Int {
    var optPage = 1
    while (optPage < frameForPage.size && frameForPage[optPage] == 0) optPage++
    for (pageNumber in 1..nextAppeal.lastIndex) {
        if (frameForPage[pageNumber] == 0) continue
        if (nextAppeal[pageNumber] > nextAppeal[optPage]) {
            optPage = pageNumber
        } else {
            if (frameForPage[pageNumber] < frameForPage[optPage]) {
                optPage = pageNumber
            }
        }
    }
    return optPage
}

// Функция, которая ищет следующее вхождение в последовательность обращений для переданной страницы
// Если больше она не встретится, то возвращается индекс конца списка + 1(Бесконечность)
fun findNextAppeal(page : Int, currentAppeal : Int, act: Act): Int {
    var indexInAct = currentAppeal + 1
    while (indexInAct < act.pages.size && act.pages[indexInAct] != page) {
        indexInAct++
    }
    return indexInAct
}

// Функция симулирующая работу OPT алгоритма
fun executeOPT(act : Act): List<Int> {
    val pageInFrame = MutableList(act.framesNumber + 1) {0}
    val frameForPage = MutableList(act.pageNumber + 1) {0}
    val substitutionsList = mutableListOf<Int>()
    val nextAppeal = MutableList(act.pageNumber + 1) {act.pages.size}
    for (indexInAct in (0..act.pages.lastIndex)) {
        val nextPage = act.pages[indexInAct]
        if (nextAppeal[nextPage] > indexInAct) {
            nextAppeal[nextPage] = indexInAct
        }
    }
    for (indexInAct in (0..act.pages.lastIndex)) {
        val nextPage = act.pages[indexInAct]
        // Если эта страница уже загружена, то ничего делать не нужно
        if (frameForPage[nextPage] != 0) {
            nextAppeal[nextPage] = findNextAppeal(nextPage, indexInAct, act)
            substitutionsList.add(0)
            continue
        }
        // Найдем свободный кадр оперативной памяти если он есть
        val indexOfEmptyFrame = findIndexOfEmptyFrame(pageInFrame)
        if (indexOfEmptyFrame != 0) {
            pageInFrame[indexOfEmptyFrame] = nextPage
            frameForPage[nextPage] = indexOfEmptyFrame
            substitutionsList.add(indexOfEmptyFrame)
            nextAppeal[nextPage] = findNextAppeal(nextPage, indexInAct, act)
            continue
        }
        // Если все кадры заняты, но нужно какой-то заменить,
        // тогда найдем оптимальный(тот к которому дольше всех не будет обращений)
        val optPage = findOPTPage(nextAppeal, frameForPage)
        substitutionsList.add(frameForPage[optPage])
        pageInFrame[frameForPage[optPage]] = nextPage
        frameForPage[nextPage] = frameForPage[optPage]
        frameForPage[optPage] = 0
        nextAppeal[optPage] = findNextAppeal(optPage, nextAppeal[optPage], act)
        nextAppeal[nextPage] = findNextAppeal(nextPage, indexInAct, act)
    }
    return substitutionsList
}