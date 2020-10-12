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
    val inputFilename = args[0]
    val acts = readInputFile(inputFilename)
    clearOutputFile(createPathToOutputFile(inputFilename, "FIFO"))
    clearOutputFile(createPathToOutputFile(inputFilename, "LRU"))
    clearOutputFile(createPathToOutputFile(inputFilename, "OPT"))
    for (actNumber in 0..acts.lastIndex) {
        // Симуляция может пройти только если входные данные заданы верно.
        if (acts[actNumber].pageNumber != -1) {
            // Симуляция алгоритмов на данном примере
            val resultsOfFIFO = executeAlgorithm(acts[actNumber], Algorithms.FIFO)
            val resultsOfLRU = executeAlgorithm(acts[actNumber], Algorithms.LRU)
            val resultsOfOPT = executeAlgorithm(acts[actNumber], Algorithms.OPT)

            // Вывод логов в отдельный файл для каждого алгоритма
            outputLogsOfAlgo(resultsOfFIFO,
                    createPathToOutputFile(inputFilename, "FIFO"), actNumber + 1)
            outputLogsOfAlgo(resultsOfLRU,
                    createPathToOutputFile(inputFilename, "LRU"), actNumber + 1)
            outputLogsOfAlgo(resultsOfOPT,
                    createPathToOutputFile(inputFilename, "OPT"), actNumber + 1)
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
    return results.count { it != 0 }
}

