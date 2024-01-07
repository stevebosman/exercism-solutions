#!/usr/bin/env bash

main () {
    STRING=${1^^}
    CHARS=${STRING//[^A-Z]/}
    for c in {A..Z}; do
        if [[ "$CHARS" != *"$c"* ]]; then
            echo "false"
            exit 0
        fi
    done
    echo "true"
}

# call main with all of the positional arguments
main "$@"
