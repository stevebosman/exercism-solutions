#!/usr/bin/env bash

main () {
    if (( $# != 1 )) || [[ -n "${1//[0-9]}" ]]; then
        >&2 echo "Usage: leap.sh <year>"
        exit 1
    fi

    local year=$(( $1 ))
    if (( year % 4 == 0 )) && (( year % 100 != 0 )) || (( year % 400 == 0 )); then
        echo "true"
    else
        echo "false"
    fi
}

main "$@"
