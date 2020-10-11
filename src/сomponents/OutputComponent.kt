package сomponents

import java.io.File


// Функция, которая задает путь до файла output.txt в той же папке что и входные данные
fun createPathToOutputFile(InputFilename : String, outputFileEnding : String): File {
    return File(File(InputFilename).parent + "/output$outputFileEnding.txt")
}

// функция, которая чистит файл
fun clearOutputFile(outputFile : File) {
    outputFile.writeText("")
}

// Вывод логов(подробной информации о симуляции алгоритма) и сообщения о местоположении файла
fun outputLogsOfAlgo(logs : List<Int?>, needClear : Boolean, outputFile: File, testNumber : Int) {
    if (needClear) clearOutputFile(outputFile)
    outputFile.appendText("Logs for test $testNumber\n")
    for (value in logs) {
        if (value == 0) {
            outputFile.appendText("Nothing changed\n")
        }
        else {
            outputFile.appendText("Card number $value has been overwritten\n")
        }
    }
    outputFile.appendText("\n\n")
    println("Test number $testNumber was saved to ${outputFile.path}")
}