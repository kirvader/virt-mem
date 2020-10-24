import сomponents.Act

// вспомогательные функции для OPT
// Функция которая ищет страницу, в оперативной памяти, которую нужно заменить в соответствии с алгоритмом
fun findOPTPage(nextAppeal : List<Int>, frameForPage : List<Int>) : Int {
    var optPage = 1
    while (optPage < frameForPage.size && frameForPage[optPage] == 0) optPage++
    for (pageNumber in 1..nextAppeal.lastIndex) {
        if (frameForPage[pageNumber] == 0) continue
        if (nextAppeal[pageNumber] > nextAppeal[optPage]) {
            optPage = pageNumber
        }
    }
    return optPage
}

// Функция, которая ищет следующее вхождение в последовательность обращений для переданной страницы
// Если больше она не встретится, то возвращается индекс конца списка + 1(Бесконечность)
fun findNextAppeal(page : Int, currentAppeal : Int, act: Act): Int {
    var indexInAct = currentAppeal + 1
    while (indexInAct < act.pages.size && act.pages[indexInAct] != page) indexInAct++
    return indexInAct
}
