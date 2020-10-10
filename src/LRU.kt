import сomponents.Act

// вспомогательная функция для LRU
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