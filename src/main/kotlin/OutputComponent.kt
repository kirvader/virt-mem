package Components

import java.io.File

// Функция, которая задает путь до файла output.txt в той же папке что и входные данные
fun createPathToOutputFile(InputFilename : String): File {
    return File(InputFilename).resolve("../output.txt")
}

// функция, которая чистит файл
fun clearOutputFile(outputFile : File) {
    outputFile.writeText("")
}

// Вывод данных и сообщения о местоположении файла
fun outputData(data : String, InputFilename: String, needClear : Boolean) {
    val outputFile : File = createPathToOutputFile(InputFilename)
    if (needClear) clearOutputFile(outputFile)
    outputFile.appendText(data)
    println("The answer was saved to ${outputFile.absolutePath}")
}