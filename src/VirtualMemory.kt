@file:JvmName("VirtualMemory")

import Components.*

fun main(args : Array<String>) {
    // Проверка аргументов
    argsCheck(args)
    // Чтение входных данных из файла, указанного в аргументах
    val acts = readInputFile(args[0])
    for (actNumber in 0..(acts.size - 1)) {
        // Симуляция алгоритмов на данном примере
        val resultsOfFIFO = executeFIFO(acts[actNumber])
        val resultsOfLRU = executeLRU(acts[actNumber])
        val resultsOfOPT = executeOPT(acts[actNumber])
        // Вывод логов в отдельный файл для каждого алгоритма
        outputLogsOfAlgo(resultsOfFIFO, actNumber == 0, createPathToOutputFile(args[0], "FIFO"), actNumber + 1)
        outputLogsOfAlgo(resultsOfLRU, actNumber == 0, createPathToOutputFile(args[0], "LRU"), actNumber + 1)
        outputLogsOfAlgo(resultsOfOPT, actNumber == 0, createPathToOutputFile(args[0], "OPT"), actNumber + 1)
        // Вывод общего сравнения алгоритмов на данном тесте
        println("On test number ${actNumber + 1}:")
        println("FIFO algorithm made ${countSubstitutions(resultsOfFIFO)} substitutions")
        println("LRU algorithm made ${countSubstitutions(resultsOfLRU)} substitutions")
        println("OPT algorithm made ${countSubstitutions(resultsOfOPT)} substitutions")
        println() // перевод строки для лучшей видимости отдельных тестов
    }
}
// Функция, которая вычисляет количество замещений, по результату работа алгоритма
fun countSubstitutions(results : List<Int?>) : Int {
    var substitutions : Int = 0
    for (result in results) {
        if (result != null) substitutions++
    }
    return substitutions
}

