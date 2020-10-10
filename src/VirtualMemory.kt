@file:JvmName("VirtualMemory")

import сomponents.*

fun main(args : Array<String>) {
    // Проверка аргументов
    argsCheck(args)
    // Если тест нужно сгенерировать
    if ((args.size == 2 && args[1] == "generate") || (args.size == 4)) {
        generateTest(args)
    }
    // Чтение входных данных из файла, указанного в аргументах
    val acts = readInputFile(args[0])
    for (actNumber in 0..acts.lastIndex) {
        // Симуляция может пройти только если входные данные заданы верно.
        if (acts[actNumber].pageNumber != -1) {
            // Симуляция алгоритмов на данном примере
            val resultsOfFIFO = executeFIFO(acts[actNumber])
            val resultsOfLRU = executeLRU(acts[actNumber])
            val resultsOfOPT = executeOPT(acts[actNumber])

            // Вывод логов в отдельный файл для каждого алгоритма
            outputLogsOfAlgo(resultsOfFIFO, actNumber == 0,
                    createPathToOutputFile(args[0], "FIFO"), actNumber + 1)
            outputLogsOfAlgo(resultsOfLRU, actNumber == 0,
                    createPathToOutputFile(args[0], "LRU"), actNumber + 1)
            outputLogsOfAlgo(resultsOfOPT, actNumber == 0,
                    createPathToOutputFile(args[0], "OPT"), actNumber + 1)
            // Вывод общего сравнения алгоритмов на данном тесте
            println("On test number ${actNumber + 1}:")
            println("FIFO algorithm made ${countSubstitutions(resultsOfFIFO)} substitutions")
            println("LRU algorithm made ${countSubstitutions(resultsOfLRU)} substitutions")
            println("OPT algorithm made ${countSubstitutions(resultsOfOPT)} substitutions")
        }
        println() // перевод строки для лучшей видимости отдельных тестов

    }
}

// Функция, которая вычисляет количество замещений, по результату работа алгоритма
fun countSubstitutions(results : List<Int>) : Int {
    var substitutions = 0
    for (result in results) {
        if (result != 0) substitutions++
    }
    return substitutions
}

