#!/usr/bin/env bash

main () {
    local word=${1^^}
    local length=${#word}
    local total=0
    for ((i = 0; i < length; i++)); do
        char="${word:i:1}"
        if [[ "AEIOULNRST" =~ $char ]]; then
            total=$(( total + 1 ))
        elif [[ "DG" =~ $char ]]; then
            total=$(( total + 2 ))
        elif [[ "BCMP" =~ $char ]]; then
            total=$(( total + 3 ))
        elif [[ "FHVWY" =~ $char ]]; then
            total=$(( total + 4 ))
        elif [[ "K" =~ $char ]]; then
            total=$(( total + 5 ))
        elif [[ "JX" =~ $char ]]; then
            total=$(( total + 8 ))
        elif [[ "QZ" =~ $char ]]; then
            total=$(( total + 10 ))
        fi
    done
    echo "$total"
}

# call main with all of the positional arguments
main "$@"
