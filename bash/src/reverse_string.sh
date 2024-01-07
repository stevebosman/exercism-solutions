#!/usr/bin/env bash

main () {
    local word=$1
    local length=${#word}
    local reverse=""
    for ((i = length - 1; i >= 0; i--)); do
        local char="${word:i:1}"
        reverse+=$char
    done
    echo "$reverse"
}

# call main with all of the positional arguments
main "$@"
