import Components.createPathToOutputFile
import Components.outputLogsOfAlgo
import Components.readInputFile

fun main(args : Array<String>) {
    val acts = readInputFile(args[0])
    val fifoAct = executeFIFO(acts[0])
    val lruAct = executeLRU(acts[0])

    outputLogsOfAlgo(fifoAct, true, createPathToOutputFile(args[0], "FIFO"), 1)
    outputLogsOfAlgo(lruAct, true, createPathToOutputFile(args[0], "LRU"), 1)

}