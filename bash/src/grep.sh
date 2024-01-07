#!/usr/bin/env bash

search () {
    local pattern=$1
    if (( INSENSITIVE==1 )); then
        pattern=${pattern^^}
    fi
    local file=$2
    local index=$(( 0 ))
    local displayFileName=0
    while IFS= read -r line; do
        index=$(( index+1 ))
        if (( INSENSITIVE == 1 )); then
            local searchLine=${line^^}
        else
            local searchLine=${line}
        fi

        if [[ ( $searchLine == *$pattern* && $WHOLE_LINE == 0 ) \
            || ( $searchLine == $pattern && $WHOLE_LINE == 1 ) ]]
        then
            local lineMatches=1
        else
            local lineMatches=0
        fi

        if (( INVERT==0 )) && (( lineMatches==1 )); then
            if (( ONLY_FILE_NAMES == 0 )); then
                if (( ALWAYS_FILE_NAME == 1 )); then
                    printf "%s:" "$file"
                fi
                if (( LINE_NUMBERS == 1 )); then
                    printf "%i:" "$index"
                fi
                printf "%s\n" "$line"
            else
                displayFileName=1
            fi
        elif (( INVERT==1 )) && (( lineMatches==0 )); then
            if (( ALWAYS_FILE_NAME == 1 )); then
                printf "%s:" "$file"
            fi
            printf "%s\n" "$line"
        fi
    done < "$file"

    if (( displayFileName == 1 )); then
        printf "%s\n" "$file"
    fi
}

main () {
    LINE_NUMBERS=0
    ONLY_FILE_NAMES=0
    INSENSITIVE=0
    INVERT=0
    WHOLE_LINE=0
    ALWAYS_FILE_NAME=0

    local pattern=""
    local argIndex=0
    for value in "$@"; do
        argIndex=$(( argIndex + 1))
        if [[ $value == "-n" ]]; then
            LINE_NUMBERS=1
        elif [[ $value == "-l" ]]; then
            ONLY_FILE_NAMES=1
        elif [[ $value == "-i" ]]; then
            INSENSITIVE=1
        elif [[ $value == "-v" ]]; then
            INVERT=1
        elif [[ $value == "-x" ]]; then
            WHOLE_LINE=1
        elif [[ -z $pattern ]]; then
            pattern=$value
        else
            if (( argIndex != $# )); then
                ALWAYS_FILE_NAME=1
            fi
            search "$pattern" $value
        fi
    done
}

main "$@"
