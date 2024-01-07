#!/usr/bin/env bash

main () {
    local word
    word=$(sed 's/\s//g' <<< "$1")
    if [[ ! $word =~ ^[0-9]+$ ]]; then
        echo "false"
        exit 0
    fi

    local length=${#word}
    if (( length < 2 )); then
        echo "false"
        exit 0
    fi

    local total=0
    for ((i = 1; i <= length; i++)); do
        local char=${word:length-i:1}
        local val=$(( char ))
        if (( i%2 == 0 )); then
            (( val*=2 ))
            if (( val>9 )); then
                (( val-=9 ))
            fi
        fi
        (( total+=val ))
    done

    if (( total%10 == 0 )); then
        echo "true"
    else
        echo "false"
    fi
}

# call main with all of the positional arguments
main "$@"
