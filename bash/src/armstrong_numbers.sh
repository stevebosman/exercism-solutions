#!/usr/bin/env bash

main () {
    NUMBER=($1)
    TOTAL=0
    if (( NUMBER > 0)); then
        LEN=${#NUMBER}

        RUNNING=$NUMBER
        for (( i=1; i<=LEN; i++ )); do
          TOTAL=$(( TOTAL + (RUNNING%10)**LEN ))
          RUNNING=$(( RUNNING/10 ))
        done
    fi

    if (( TOTAL - NUMBER == 0 )); then
        echo "true"
    else
        echo "false"
    fi
}

main "$@"
