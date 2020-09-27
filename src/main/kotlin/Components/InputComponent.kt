package Components

import java.io.File

// функция, которая проверяет аргументы на наличиеи на корректность
fun argsCheck(data : Array<String>) {
    // Если аргументов нет
    if (data.isEmpty()) {
        error("Args are empty!")
    }
    if (data.size > 1) {
        error("Too many args!")
    }
    // Если введенный пользователем путь не существует
    if (!File(data[0]).exists()) {
        error("This path doesn't exist!")
    }
}

// Проверка строки на то, что она на самом деле число
fun checkIfStringIsNumber(string : String): Boolean {
    return string.matches("-?\\d+(\\.\\d+)?".toRegex())
}

// Проверка очередного теста на соответствие входным данным
fun checkActInput(splittedStringWithSizes : List<String>, splittedStringWithPagesNumbers : List<String>, testNumber : Int) {
    if (splittedStringWithSizes.size != 2) { // В первой строке должны находиться только количества страниц и кадров
        error("Something is wrong with number of sizes in test $testNumber in string number ${testNumber * 2}")
    }
    // Каждая из строк должна быть числом
    for (string in splittedStringWithSizes) {
        if (!checkIfStringIsNumber(string)) {
            error("String ${string} in test number $testNumber on string number ${testNumber * 2} is not numeric")
        }
    }
    val numberOfValuesInSecondString = splittedStringWithSizes[0].toInt()
    val numberOfFrames = splittedStringWithSizes[1].toInt()
    if (numberOfFrames >= numberOfValuesInSecondString) { // количество страниц должно быть больше количества кадров по условию
        error("Number of frames should be less than a number of pages")
    }
    for (string in splittedStringWithPagesNumbers) {
        if (!checkIfStringIsNumber(string)) { // всё должно быть числами
            error("String ${string} in test number $testNumber on string number ${testNumber * 2 + 1} is not numeric")
        }
        if (string.toInt() < 1 || string.toInt() > numberOfValuesInSecondString) { // Все числа во второй строке не должны превосходить количества страниц
            error("String ${string} in test number $testNumber on string number ${testNumber * 2 + 1} is not in range from 1 to $numberOfValuesInSecondString")
        }
    }
}

// проверка символов строки на принадлежность алфавиту
fun readInputFile(fileName: String) : List<Act> {
    val userInput = readFile(fileName)
    var acts = mutableListOf<Act>()
    for (testNumber in 0..(userInput.size - 1) / 2) {
        // Тест состоит из двух строк: в первой строке размеры M и N, во второй строке последовательность обращений
        val splittedStringWithSizes = userInput[2 * testNumber].split(' ')
        val splittedStringWithPageNumbers = userInput[2 * testNumber + 1].split(' ')

        // Проверка теста на правильность(соответствие входным данным)
        checkActInput(splittedStringWithSizes, splittedStringWithPageNumbers, testNumber)
        // Парсинг
        val numberOfFrames : Int = splittedStringWithSizes[1].toInt()
        val currentActPages : List<Int> = splittedStringWithPageNumbers.map{it.toInt()}
        acts.add(Act(currentActPages, numberOfFrames))
    }
    return acts
}

// чтение всех строк из файла
fun readFile(fileName: String): List<String>
        = File(fileName).readLines()