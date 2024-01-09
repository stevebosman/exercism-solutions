export const square = (value: number) => {
  if (value < 1 || value > 64) throw Error("value should be between 1 and 64.")
  return 2n ** BigInt(value - 1)
}

export const total = () => {
  return 2n ** BigInt(64) - 1n
}
