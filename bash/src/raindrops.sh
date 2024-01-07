#!/usr/bin/env bash

VALUE=$(($1))
RESULT=""
(( VALUE % 3 )) || RESULT+="Pling"
(( VALUE % 5 )) || RESULT+="Plang"
(( VALUE % 7 )) || RESULT+="Plong"

echo "${RESULT:-$VALUE}"
