package сomponents

import java.io.File
import kotlin.system.exitProcess

// функция, которая проверяет аргументы на наличием на корректность
fun argsCheck(data : Array<String>) {
    // Если аргументов нет
    if (data.isEmpty()) {
        println("Args are empty!")
        exitProcess(0)
    }
    if (data.size != 1 && data.size != 2 && data.size != 4) {
        println("Quantity of arguments must be 1 or 2 or 4!")
        exitProcess(0)
    }
    // Если введенный пользователем путь не существует
    if (data.size == 1 && !File(data[0]).exists()) {
        println("This path doesn't exist!")
        exitProcess(0)
    }
    if (data.size == 2 && data[1] != "generate") {
        println("If you want to generate test with default sizes then you have to write generate as second argument!")
        exitProcess(0)
    }
    if (data.size == 4 &&
            (!checkIfStringNumber(data[1]) || !checkIfStringNumber(data[2]) || !checkIfStringNumber(data[3]))) {
        println("Second, third and fourth arguments should be integers!")
        exitProcess(0)
    }
}

// Проверка строки на то, что она на самом деле число
fun checkIfStringNumber(string : String): Boolean {
    return string.matches("-?\\d+(\\.\\d+)?".toRegex())
}

// Проверка очередного теста на соответствие входным данным
fun checkActInput(splitStringWithSizes : List<String>,
                  splitStringWithPagesNumbers : List<String>, testNumber : Int) : Boolean {
    if (splitStringWithSizes.size != 2) { // В первой строке должны находиться только количества страниц и кадров
        println("Something is wrong with number of sizes in test $testNumber in string number ${testNumber * 2}")
        return false
    }
    // Каждая из строк должна быть числом
    for (string in splitStringWithSizes) {
        if (!checkIfStringNumber(string)) {
            println("String $string in test number $testNumber on string number ${testNumber * 2} is not numeric")
            return false
        }
    }
    val numberOfValuesInSecondString = splitStringWithSizes[0].toInt()
    val numberOfFrames = splitStringWithSizes[1].toInt()
    // количество страниц должно быть больше количества кадров по условию
    if (numberOfFrames >= numberOfValuesInSecondString) {
        println("Test number $testNumber.Number of frames should be less than a number of pages")
        return false
    }
    splitStringWithPagesNumbers.forEach { string ->
        if (!checkIfStringNumber(string)) { // всё должно быть числами
            println("String $string in test number $testNumber on string number ${testNumber * 2 + 1} is not numeric")
            return false
        }
        // Все числа во второй строке не должны превосходить количества страниц
        if (string.toInt() < 1 || string.toInt() > numberOfValuesInSecondString) {
            println("String $string in test number $testNumber on string number " +
                    "${testNumber * 2 + 1} is not in range from 1 to $numberOfValuesInSecondString")
            return false
        }
    }
    return true
}

// проверка символов строки на принадлежность алфавиту
fun readInputFile(fileName: String) : List<Act> {
    val userInput = readFile(fileName)
    val acts = mutableListOf<Act>()
    for (testNumber in 0..(userInput.lastIndex) / 2) {
        // Тест состоит из двух строк: в первой строке размеры M и N, во второй строке последовательность обращений
        val splitStringWithSizes = userInput[2 * testNumber].split(' ')
        val splitStringWithPageNumbers = userInput[2 * testNumber + 1].split(' ')

        // Проверка теста на правильность(соответствие входным данным)
        // Если какой-то из тестов введен неверно, то обозначим его как неверный
        if (!checkActInput(splitStringWithSizes, splitStringWithPageNumbers, testNumber)) {
            acts.add(Act(listOf(), -1, -1))
            continue
        }
        // Если же проверка прошла успешно
        // Разбиение на числа отвечающие за размеры и за последовательность обращений
        val numberOfPages : Int = splitStringWithSizes[0].toInt()
        val numberOfFrames : Int = splitStringWithSizes[1].toInt()
        val currentActPages : List<Int> = splitStringWithPageNumbers.map{it.toInt()}
        acts.add(Act(currentActPages, numberOfFrames, numberOfPages))
    }
    return acts
}

// чтение всех строк из файла
fun readFile(fileName: String): List<String>
        = File(fileName).readLines()