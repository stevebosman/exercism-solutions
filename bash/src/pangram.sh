#!/usr/bin/env bash

main () {
    ALPHABET="ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    STRING=${1^^}
    CHARS=${STRING//[^A-Z]/}
    for (( i=0; i<${#ALPHABET}; i++ )); do
        c="${ALPHABET:$i:1}"
        if [[ "$CHARS" != *"$c"* ]]; then
            echo "false"
            return
        fi
    done
    echo "true"
}

# call main with all of the positional arguments
main "$@"
