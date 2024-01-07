#!/usr/bin/env bash

main () {
  if [[ $1 == "total" ]]; then
      echo "18446744073709551615"
  else
      local square=$(( $1 - 1 ))
      if (( square < 0 )) || (( square > 63 )); then
        echo "Error: invalid input"
        exit 1
      fi
      local grains=$(( 1<<square ))
      echo $(printf "%u" $grains )
  fi
}

# call main with all of the positional arguments
main "$@"
