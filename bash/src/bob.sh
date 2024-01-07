#!/usr/bin/env bash

hasUpper () {
    STRING=${1}
    if [[ "${STRING}" == *[[:upper:]]* ]]; then
        return 0
    else
        return 1
    fi
}

hasLower () {
    STRING=${1}
    if [[ "${STRING}" == *[[:lower:]]* ]]; then
        return 0
    else
        return 1
    fi
}

isAllCaps () {
    STRING=${1}
    if hasUpper "${STRING}" && ! hasLower "${STRING}"; then
        return 0
    else
        return 1
    fi
}

isQuestion () {
    STRING=${1}
    if [[ "$STRING" =~ \?$ ]]; then
        return 0
    else
        return 1
    fi
}

main () {
    STRING=$(sed 's/\s//g' <<< "$1")
    if [[ -z "$STRING" ]]; then
        echo "Fine. Be that way!"
    elif isAllCaps "${STRING}"; then
        if isQuestion "${STRING}"; then
          echo "Calm down, I know what I'm doing!"
        else
          echo "Whoa, chill out!"
        fi
    elif isQuestion "${STRING}"; then
        echo "Sure."
    else
        echo "Whatever."
    fi
}

main "$@"
