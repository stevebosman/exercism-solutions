#!/usr/bin/env bash

main () {
    epoch=$(date -d"$1" +%s)
    epoch=$(( epoch + 1000000000 ))

    printf '%(%Y-%m-%dT%H:%M:%S)T\n' "$epoch"
}

main "$@"
