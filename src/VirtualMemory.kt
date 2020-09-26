import Components.readInputFile

fun main(args : Array<String>) {
    val acts = readInputFile(args[0])
    executeLRU(acts[0])
}