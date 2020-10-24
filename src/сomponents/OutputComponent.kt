package сomponents

import java.io.File
// из пути выдает имя файла без расширения
fun filenameToName(filename : String): String {
    var dotIndex = filename.lastIndex
    while (filename[dotIndex] != '.' && dotIndex >= 0) dotIndex--
    var lastSlashIndex = filename.lastIndex
    while (filename[lastSlashIndex] != '/' && lastSlashIndex >= 0) lastSlashIndex--
    return filename.substring(lastSlashIndex + 1, dotIndex)
}
// Функция, которая задает путь до файла output.txt в той же папке что и входные данные
fun createPathToOutputFile(InputFilename : String, outputFileEnding : String): File {
    return File(File(InputFilename).parent + "/output-${filenameToName(InputFilename)}$outputFileEnding.txt")
}

// функция, которая чистит файл
fun clearOutputFile(outputFile : File) {
    outputFile.writeText("")
}

// Вывод логов(подробной информации о симуляции алгоритма) и сообщения о местоположении файла
fun outputLogsOfAlgo(logs : List<Int?>, outputFile: File, testNumber : Int) {
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