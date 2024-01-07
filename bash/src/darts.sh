#!/usr/bin/env bash

isNumeric () {
    if [[ "$1" =~ ^-?[0-9.]+$ ]]; then
        return 0
    else
        return 1
    fi
}

main () {
    if [[ "$#" != 2 ]]; then
        echo "There must be two numeric parameters"
        exit 1
    fi
    if ! isNumeric $1; then
        echo "First parameter must be numeric"
        exit 1
    fi
    if ! isNumeric $2; then
        echo "Second parameter must be numeric"
        exit 1
    fi

    local x=$(bc <<< "scale=5; ${1#-} - 0.001")
    local y=$(bc <<< "scale=5; ${2#-} - 0.001")
    local rf=$(bc <<< "scale=0; $x*$x + $y*$y")
    local r=${rf%%.*}

    # echo "($x,$y): $rf $r"
    local score
    if (( r >= 100 )); then
        score=0
    elif (( r >= 25 )); then
        score=1
    elif (( r >= 1 )); then
        score=5
    else
        score=10
    fi
    echo "$score"
}

# call main with all of the positional arguments
main "$@"
