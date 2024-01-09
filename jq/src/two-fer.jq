# You might want to look at:
#
# - the alternative operator:
#   https://jqlang.github.io/jq/manual/v1.6/#Alternativeoperator://
#
# - string interpolation:
#   https://jqlang.github.io/jq/manual/v1.6/#Stringinterpolation-%5C(foo)

"One for \(.name // "you"), one for me."
