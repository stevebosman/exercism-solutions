export const COLORS = [
      'black',
      'brown',
      'red',
      'orange',
      'yellow',
      'green',
      'blue',
      'violet',
      'grey',
      'white',
    ]

export const colorCode = (color:string) => {
  return COLORS.indexOf(color)
}

export function decodedValue([tens, units]: [string, string]) {
  return 10 * colorCode(tens) + colorCode(units)
}
