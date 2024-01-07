#!/usr/bin/env bash

main () {
    local method=$1
    local word=${2,,}

    cipher=$(echo "${word//[ ,.]/}" | tr 'abcdefghijklmnopqrstuvwxyz' 'zyxwvutsrqponmlkjihgfedcba')
    if [[ "$method" == "encode" ]]; then
        result=$(echo "$cipher" | sed 's/.\{5\}/& /g')
    else
        result=$cipher
    fi
    echo $result
}

# call main with all of the positional arguments
main "$@"
