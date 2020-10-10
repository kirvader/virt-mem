package сomponents

import java.io.File
import kotlin.random.Random

// Генератор рандомного теста
fun generateTest(args : Array<String>) {
    argsCheck(args)
    // дефолтные значения
    var numberOfDifferentPages : Int = 100
    var framesNumber : Int = 20
    var pagesNumber : Int = 1000
    // Если пользователь передал значения, то используем их
    if (args.size == 4) {
        numberOfDifferentPages = args[1].toInt()
        framesNumber = args[2].toInt()
        pagesNumber = args[3].toInt()
    }
    val sequence = MutableList<Int>(pagesNumber) { Random.nextInt(1, numberOfDifferentPages) }
    val outputFile = File(args[0])
    outputFile.writeText("$numberOfDifferentPages $framesNumber\n")
    // Для правильного чтения
    if (pagesNumber > 0) outputFile.appendText(sequence[0].toString())
    for (index in 1..(sequence.lastIndex)) {
        outputFile.appendText(" ${sequence[index]}")
    }
    println("Generated test was saved to ${args[0]}")
}