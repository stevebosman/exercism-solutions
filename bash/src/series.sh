#!/usr/bin/env bash

main () {
    local series=$1
    if [[ $series == "" ]]; then
        echo "series cannot be empty"
        exit 1
    fi

    local sublen=$2
    if (( sublen == 0 )); then
        echo "slice length cannot be zero"
        exit 1
    fi
    if (( sublen < 0 )); then
        echo "slice length cannot be negative"
        exit 1
    fi

    local length=${#series}
    if (( sublen > length )); then
        echo "slice length cannot be greater than series length"
        exit 1
    fi

    local result=""
    for ((i = 0; i < length-sublen+1; i++)); do
        if (( i > 0)); then
            result="${result} "
        fi
        result="${result}${series:i:sublen}"
    done
    echo "$result"
}

main "$@"
