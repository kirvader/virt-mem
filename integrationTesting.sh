#!/bin/bash

TEST_COUNT=10
result=0
for ((i = 1 ; i <= $TEST_COUNT ; i++)); do
    num=$i
    echo "test $num"
    java -jar build/libs/VirtMem-1.0-SNAPSHOT.jar integrationTests/test${num}.in
    if [[ $? != 0 ]]; then
        echo "FAILED";
        result=1;
        continue;
    fi;
    if (diff integrationTests/output-test${num}FIFO.txt integrationTests/expectedFortest${num}FIFO.txt); then
        echo "FIFO PASSED"
    else
        echo "FIFO FAILED"
        result=1
    fi;
    if (diff integrationTests/output-test${num}LRU.txt integrationTests/expectedFortest${num}LRU.txt); then
        echo "LRU PASSED"
    else
        echo "LRU FAILED"
        result=1
    fi;
    if (diff integrationTests/output-test${num}OPT.txt integrationTests/expectedFortest${num}OPT.txt); then
        echo "OPT PASSED"
    else
        echo "OPT FAILED"
        result=1
    fi;
done
exit $result

