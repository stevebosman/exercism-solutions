#!/usr/bin/env bash

exitWithInvalidArguments () {
    >&2 echo "Error: invalid arguments - $1"
    >&2 echo "usage: clock.sh h m [{+|-} delta]"
    exit 1
}

main () {
    if (( $# != 2 )) && (( $# != 4 )); then
        exitWithInvalidArguments "wrong number of arguments"
    elif [[ -n "${1//[-0-9]}" ]] || [[ -n "${2//[-0-9]}" ]]; then
        exitWithInvalidArguments "hour and minute must be numeric"
    elif (( $# == 4 )) && [[ -n "${3//[+-]}" ]]; then
        exitWithInvalidArguments "invalid delta operation"
    elif (( $# == 4 )) && [[ -n "${4//[-0-9]}" ]]; then
        exitWithInvalidArguments "delta must be numeric"
    fi
    local MINUTES_IN_DAY=1440
    local raw=$(( $1 * 60 + $2 ))
    if [[ $3 == "+" ]]; then
        raw=$(( raw + $4 ))
    elif [[ $3 == "-" ]]; then
        raw=$(( raw - $4 ))
    fi
    while (( raw < 0 )); do
        raw=$(( raw + MINUTES_IN_DAY ))
    done
    raw=$(( raw % MINUTES_IN_DAY ))
    printf "%02i:%02i" $(( raw / 60 )) $(( raw % 60 ))
}

main "$@"
