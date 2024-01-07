#!/usr/bin/env bash

main () {
    local word=${1^^}
    local length=${#word}
    local total=0
    for ((i = 0; i < length; i++)); do
        local char="${word:i:1}"
        local score=0
        if [[ $char == [AEIOULNRST] ]]; then
            score=1
        elif [[ $char == [DG] ]]; then
            score=2
        elif [[ $char == [BCMP] ]]; then
            score=3
        elif [[ $char == [FHVWY] ]]; then
            score=4
        elif [[ $char == "K" ]]; then
            score=5
        elif [[ $char == [JX] ]]; then
            score=8
        elif [[ $char == [QZ] ]]; then
            score=10
        fi
        (( total+=score ))
    done
    echo "$total"
}

# call main with all of the positional arguments
main "$@"
