import Components.argsCheck
import Components.createPathToOutputFile
import Components.outputLogsOfAlgo
import Components.readInputFile

fun main(args : Array<String>) {
    argsCheck(args)
    val acts = readInputFile(args[0])
    var logsForFIFOActs = mutableListOf<List<Int?>>()
    var logsForLRUActs = mutableListOf<List<Int?>>()
    var logsForOPTActs = mutableListOf<List<Int?>>()
    for (actNumber in 0..(acts.size - 1)) {
        outputLogsOfAlgo(executeFIFO(acts[actNumber]), actNumber == 0, createPathToOutputFile(args[0], "FIFO"), actNumber + 1)
        outputLogsOfAlgo(executeLRU(acts[actNumber]), actNumber == 0, createPathToOutputFile(args[0], "LRU"), actNumber + 1)
        outputLogsOfAlgo(executeOPT(acts[actNumber]), actNumber == 0, createPathToOutputFile(args[0], "OPT"), actNumber + 1)
    }

}

