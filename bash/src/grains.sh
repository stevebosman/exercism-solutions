#!/usr/bin/env bash

main () {
  if [[ $1 == "total" ]]; then
    echo "18446744073709551615"
  elif (( $1 > 0 )) && (( $1 <= 64 )); then
    printf "%u" "$(( 1 << $1 - 1 ))"
  else
    echo "Error: invalid input"
    exit 1
  fi
}

# call main with all of the positional arguments
main "$@"
