#!/usr/bin/env bash

IFS='- _*'
acronym=""
for word in $1; do
  letter="${word:0:1}"
  acronym+=${letter^^}
done

echo "$acronym"
