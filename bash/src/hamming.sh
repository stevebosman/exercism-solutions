#!/usr/bin/env bash

if (( ${#} != 2)); then
  echo "Usage: hamming.sh <string1> <string2>"
  exit 1
fi

DNA1=$1
DNA2=$2
if (( ${#DNA1} != ${#DNA2})); then
  echo "strands must be of equal length"
  exit 1
fi

DIFF=0
if [[ "$DNA1" != "$DNA2" ]]; then
  for (( i=0; i<${#DNA1}; i++ )); do
    if [[ "${DNA1:$i:1}" != "${DNA2:$i:1}" ]]; then
      DIFF=$(( $DIFF + 1 ))
    fi
  done
fi

echo $DIFF
